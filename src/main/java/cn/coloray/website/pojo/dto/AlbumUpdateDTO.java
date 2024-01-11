package cn.coloray.website.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改相册数据的DTO类
 *
 * @author Phuoc
 * @version 0.0.1
 */
@Data
public class AlbumUpdateDTO implements Serializable {

    /**
     * 相册名称
     */
    private String name;
    /**
     * 相册简介
     */
    private String description;
    /**
     * 排序序号
     */
    private Integer sort;

}
