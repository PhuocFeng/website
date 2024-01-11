package cn.coloray.website.controller;

import cn.coloray.website.ex.ServiceException;
import cn.coloray.website.pojo.dto.AlbumAddNewDTO;
import cn.coloray.website.pojo.vo.AlbumListItemVO;
import cn.coloray.website.service.IAlbumService;
import cn.coloray.website.web.JsonResult;
import cn.coloray.website.web.ServiceCode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "1.相册管理模块")
@Slf4j
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    IAlbumService albumService;

    // http://localhost:9080/album/add-new?name=TestAlbum001&description=TestDescription001&sort=88
    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 400)
    @ApiImplicitParam(name="id",value = "相册ID",required = true,example = "9527",dataType = "long")
    @RequestMapping(value = "/add-new",method = RequestMethod.GET)
    public JsonResult add(@Valid AlbumAddNewDTO addNewDTO) {
            albumService.addNew(addNewDTO);
            return JsonResult.ok();

        }

    // http://localhost:9080/albums/9527/delete
    @ApiOperation("删除相册")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name="id",value = "相册ID",required = true,example = "9527",dataType = "long")
    @PostMapping(value = "/{id:[0-9]+}delete")
    public JsonResult delete(@Range(min=1,max=1000,message = "删除相册失败，相册ID非法！") @PathVariable Long id){
        albumService.delete(id);
        return JsonResult.ok();
    }

    // http://localhost:9080/album/list
    @ApiOperation("查询相册")
    @ApiOperationSupport(order = 200)
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<AlbumListItemVO> list() {
        List<AlbumListItemVO> list = albumService.list();
        return list;

    }

}
