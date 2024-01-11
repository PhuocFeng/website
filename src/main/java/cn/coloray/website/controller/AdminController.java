package cn.coloray.website.controller;

import cn.coloray.website.pojo.dto.AdminLoginDTO;
import cn.coloray.website.pojo.vo.AdminListItemVO;
import cn.coloray.website.security.AdminDetails;
import cn.coloray.website.service.IAdminService;
import cn.coloray.website.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "管理员模块")
@RequestMapping("/admin")
public class AdminController {
    @Autowired(required = false)
    IAdminService adminService;

    @ApiOperation("登录操作")
    @ApiOperationSupport(order = 300)
    @PostMapping("/login")
    public JsonResult<String> logByUsername(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理【管理员登录】的请求，参数：{}", adminLoginDTO);
        String jwt = adminService.login(adminLoginDTO);
        return JsonResult.ok(jwt);
    }

    @ApiOperation("管理员列表")
    @ApiOperationSupport(order = 600)
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
//                                                                     ↓↓↓↓↓↓↓↓↓↓↓↓ 自定义类型的当事人
    public JsonResult<List<AdminListItemVO>> list(@AuthenticationPrincipal AdminDetails adminDetails) {
        log.debug("开始处理【查询管理员列表】的请求，参数：无");
        log.debug("当事人：{}", adminDetails); // 可以获取ID
        log.debug("当事人的ID：{}", adminDetails.getId());
        log.debug("当事人的用户名：{}", adminDetails.getUsername());
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }
}
