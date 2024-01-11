package cn.coloray.website.service.impl;


import cn.coloray.website.ex.ServiceException;
import cn.coloray.website.mapper.AdminMapper;
import cn.coloray.website.pojo.dto.AdminAddNewDTO;
import cn.coloray.website.pojo.dto.AdminLoginDTO;
import cn.coloray.website.pojo.dto.AdminUpdateDTO;
import cn.coloray.website.pojo.entity.Admin;
import cn.coloray.website.pojo.vo.AdminListItemVO;
import cn.coloray.website.pojo.vo.AdminLogInfoVO;
import cn.coloray.website.security.AdminDetails;
import cn.coloray.website.service.IAdminService;
import cn.coloray.website.web.ServiceCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Value("${website.jwt.secret-Key")
    private String secretKey;

    @Autowired
    AdminMapper adminMapper;

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Override
    public int insert(AdminAddNewDTO adminAddNewDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO,admin);
        int rows = adminMapper.insert(admin);
        if(rows==0){
            log.debug("添加管理员失败");
            throw new ServiceException(ServiceCode.ERR_INSERT, "添加管理员失败");
        }
        return rows;
    }

    @Override
    public int insertBatch(List<AdminAddNewDTO> list) {

        ArrayList<Admin> admins = new ArrayList<>();
        for (AdminAddNewDTO adminAddNewDTO:list) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminAddNewDTO,admin);
            admins.add(admin);
        }
        int rows = adminMapper.insertBatch(admins);
        if(rows==0){
            log.debug("添加管理员失败");
            throw new ServiceException(ServiceCode.ERR_INSERT, "添加管理员失败");
        }
        return rows;
    }

    @Override
    public int delete(Long id) {
        int rows = adminMapper.deleteById(id);
        if(rows==0){
            log.debug("删除管理员失败");
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除管理员失败");
        }
        return rows;
    }

    @Override
    public int deleteBatch(Long[] ids) {
        int rows = adminMapper.deleteByIds(ids);
        if(rows==0){
            log.debug("批量删除管理员失败");
            throw new ServiceException(ServiceCode.ERR_DELETE, "批量删除管理员失败");
        } return 0;
    }

    @Override
    public int update(AdminUpdateDTO adminUpdateDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateDTO,admin);
        int rows = adminMapper.update(admin);
        if(rows==0){
            log.debug("更新管理员失败");
            throw new ServiceException(ServiceCode.ERR_UPDATE, "更新管理员失败");
        }
        return rows;
    }

    @Override
    public int count() {
        int count = adminMapper.count();
        return count;
    }

    @Override
    public AdminLogInfoVO getUserByUsername(String username) {
        AdminLogInfoVO admin = adminMapper.getUserByName(username);
        if(admin==null){
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,"没有此用户");
        }
        return admin;
    }

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理【管理员登录】的业务，参数：{}", adminLoginDTO);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        adminLoginDTO.getUsername(),
                        adminLoginDTO.getPassword()
                );
        //authenticate方法会调用UserDetailsService的loadUserByUsername方法
        Authentication authenticateResult = authenticationManager.authenticate(authentication);
        AdminDetails principal = (AdminDetails) authenticateResult.getPrincipal();
        log.debug("认证结果：{}", principal);
        //将认证结果封装到SecurityContext中
        Date date = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", principal.getId());
        claims.put("username", principal.getUsername());

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                // Signature
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 完成
                .compact();
        log.debug("生成的JWT：{}", jwt);
        return jwt;
    }

    @Override
    public List<AdminListItemVO> list() {
        List<AdminListItemVO> list = adminMapper.listItems();
        log.debug("查询到管理员列表：{}", list);
        if (CollectionUtils.isEmpty(list)){
            log.debug("查询到管理员列表为空");
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,"没有查询到管理员列表");
        }
        return list;
    }


}
