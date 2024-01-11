package cn.coloray.website;

import cn.coloray.website.pojo.dto.AlbumAddNewDTO;
import cn.coloray.website.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    IAlbumService iAlbumService;


    @Test
    void addTest(){
        AlbumAddNewDTO addNewDTO = new AlbumAddNewDTO();
        addNewDTO.setName("测试名称001");
        addNewDTO.setDescription("测试简介001");
        addNewDTO.setSort(101); // 注意：由于MySQL中表设计的限制，此值只能是[0,255]区间内的

        try{
            iAlbumService.addNew(addNewDTO);
            log.debug("添加相册成功！");
        }catch (RuntimeException e){
            log.debug("添加相册失败，相册名称已经被占用！");
        }
    }
}
