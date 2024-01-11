package cn.coloray.website.service;


import cn.coloray.website.pojo.dto.AlbumAddNewDTO;
import cn.coloray.website.pojo.vo.AlbumListItemVO;

import java.util.List;

public interface IAlbumService {

    void addNew(AlbumAddNewDTO addNewDTO);
    void delete(Long id);

    List<AlbumListItemVO> list();
}
