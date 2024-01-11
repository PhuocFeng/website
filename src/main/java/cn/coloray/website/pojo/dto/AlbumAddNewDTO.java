package cn.coloray.website.pojo.dto;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加相册的DTO类
 *
 * @author Phuoc
 * @version 0.0.1
 */
@Data
public class AlbumAddNewDTO implements Serializable {

    /**
     * 相册名称
     */
    @ApiModelProperty(value = "相册名称", required = true, example = "小米手机的相册")
    @NotNull(message = "添加相册失败，必须提交相册名称！")
    private String name;

    /**
     * 相册简介
     */
    @ApiModelProperty(value = "相册简介", required = true, example = "小米手机的相册的简介")
    @NotNull(message = "添加相册失败，必须提交相册简介！")
    private String description;

    /**
     * 排序序号
     */
    @ApiModelProperty(value = "排序序号", required = true, example = "99")
    @NotNull(message = "添加相册失败，必须提交排序序号！")
    private Integer sort;

}