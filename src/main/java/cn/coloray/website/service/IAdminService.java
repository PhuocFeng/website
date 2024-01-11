package cn.coloray.website.service;

import cn.coloray.website.pojo.dto.AdminAddNewDTO;
import cn.coloray.website.pojo.dto.AdminLoginDTO;
import cn.coloray.website.pojo.dto.AdminUpdateDTO;
import cn.coloray.website.pojo.vo.AdminListItemVO;
import cn.coloray.website.pojo.vo.AdminLogInfoVO;

import java.util.List;

public interface IAdminService {
    int insert(AdminAddNewDTO adminAddNewDTO);
    int insertBatch(List<AdminAddNewDTO> adminAddNewDTOList);
    int delete(Long id);
    int deleteBatch(Long[] ids);
    int update(AdminUpdateDTO adminUpdateDTO);
    int count();
    AdminLogInfoVO getUserByUsername(String username);
    List<AdminListItemVO> list();
    String login(AdminLoginDTO adminLoginDTO);

}
