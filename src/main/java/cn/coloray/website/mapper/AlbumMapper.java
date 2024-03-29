package cn.coloray.website.mapper;

import cn.coloray.website.pojo.entity.Album;
import cn.coloray.website.pojo.vo.AlbumListItemVO;
import cn.coloray.website.pojo.vo.AlbumStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 处理相册数据的Mapper接口
 *
 * @author Phuoc
 * @version 0.0.1
 */
@Repository
public interface AlbumMapper {

    /**
     * 插入相册数据
     *
     * @param album 相册数据
     * @return 受影响的行数
     */
    int insert(Album album);

    /**
     * 批量插入相册数据
     *
     * @param albums 相册列表
     * @return 受影响的行数
     */
    int insertBatch(List<Album> albums);

    /**
     * 根据id删除相册数据
     *
     * @param id 相册id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据若干个id批量删除相册数据
     *
     * @param ids 若干个相册id的数组
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据id修改相册数据
     *
     * @param album 封装了相册id和新数据的对象
     * @return 受影响的行数
     */
    int update(Album album);

    /**
     * 统计相册数据的数量
     *
     * @return 相册数据的数量
     */
    int count();

    /**
     * 根据相册名称统计数据的数量
     *
     * @param name 相册名称
     * @return 匹配名称的相册数据的数量
     */
    int countByName(String name);

    /**
     * 统计非某id但名称匹配的相册数据的数量，用于检查是否存在其它数据使用了相同的名称
     * @param id 相册id
     * @param name 相册名称
     * @return 匹配名称但不匹配id的数据的数量
     */
    int countByNameAndNotId(@Param("id") Long id, @Param("name") String name);

    /**
     * 根据id查询相册数据详情
     *
     * @param id 相册id
     * @return 匹配的相册数据详情，如果没有匹配的数据，则返回null
     */
    AlbumStandardVO getStandardById(Long id);

    /**
     * 查询相册数据列表
     *
     * @return 相册数据列表
     */
    List<AlbumListItemVO> list();

}
