package cn.coloray.website.mapper;

import cn.coloray.website.pojo.entity.Admin;
import cn.coloray.website.pojo.vo.AdminListItemVO;
import cn.coloray.website.pojo.vo.AdminLogInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int insert(Admin admin);
    int insertBatch(List<Admin> list);
    int deleteById(Long id);
    int deleteByIds(Long[] ids);
    int update(Admin admin);
    int count();
    AdminLogInfoVO getUserByName(String username);
    List<AdminListItemVO> listItems();
}
