package cn.coloray.website.security;

import cn.coloray.website.mapper.AdminMapper;
import cn.coloray.website.pojo.vo.AdminLogInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);

        AdminLogInfoVO loginInfo = adminMapper.getUserByName(s);
        log.debug("从数据库查询用户名【{}】匹配的信息，结果：{}", s, loginInfo);

        if (loginInfo == null) {
            return null; // 暂时
        }
        List<String> permissions = loginInfo.getPermissions();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(String permission : permissions){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        AdminDetails adminDetails = new AdminDetails(
                loginInfo.getId(),
                loginInfo.getUsername(),
                loginInfo.getPassword(),
                loginInfo.getEnable()==1,
                authorities
        );

        return adminDetails;
    }
}
