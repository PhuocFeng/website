package cn.coloray.website.service.impl;

import cn.coloray.website.ex.ServiceException;
import cn.coloray.website.mapper.AlbumMapper;
import cn.coloray.website.pojo.dto.AlbumAddNewDTO;
import cn.coloray.website.pojo.entity.Album;
import cn.coloray.website.pojo.vo.AlbumListItemVO;
import cn.coloray.website.pojo.vo.AlbumStandardVO;
import cn.coloray.website.service.IAlbumService;
import cn.coloray.website.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理相册数据的业务实现类
 *
 * @author Phuoc
 * @version 0.0.1
 */
@Service
@Slf4j
public class IAlbumServiceImpl implements IAlbumService {

    @Autowired
    AlbumMapper albumMapper;
    @Override
    public void addNew(AlbumAddNewDTO addNewDTO) {
        log.debug("开始处理【添加相册】的业务，参数：{}", addNewDTO);
        //开始检查相册名称是否被占用！
        int count = albumMapper.countByName(addNewDTO.getName());
        if (count > 0) {
            // 如果已经被占用，抛出异常
            throw new ServiceException(ServiceCode.ERR_CONFLICT,"相册名称被占用！");
        }
        Album album = new Album();
        BeanUtils.copyProperties(addNewDTO,album);
        log.debug("即将插入数据，参数：{}", album);
        int num = albumMapper.insert(album);
        if(num!=1){
            throw new ServiceException(ServiceCode.ERR_INSERT,"添加相册失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除相册】的业务，id为：{}", id);
        AlbumStandardVO albumVO = albumMapper.getStandardById(id);
        if(albumVO == null){
            log.warn("此相册数据不存在");
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,"找不到此相册！");
        }
        int count = albumMapper.deleteById(id);
        if(count !=1){
            log.warn("");
            throw new ServiceException(ServiceCode.ERR_DELETE,"删除相册失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public List<AlbumListItemVO> list() {
        log.debug("开始处理【查询相册】的业务");
        List<AlbumListItemVO> list = albumMapper.list();
        return list;
    }


}
