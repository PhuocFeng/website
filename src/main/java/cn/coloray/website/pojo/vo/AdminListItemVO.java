package cn.coloray.website.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//generate a AdminListItemVo to Encapsulates the administrator list returned from the database query
@Data
public class AdminListItemVO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
    private Integer enable;
    private String lastLoginIp;
    private Integer loginCount;
    private LocalDateTime lastLoginTime;
    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}
