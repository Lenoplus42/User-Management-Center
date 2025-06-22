package com.leno.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * stores user in UMC
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * nickname
     */
    private String username;

    /**
     * login account
     */
    private String account;

    /**
     * 
     */
    private String avatarUrl;

    /**
     * 
     */
    private Integer gender;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String email;

    /**
     * user status 0 - normal
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * time when account is updated
     */
    private Date updateTime;

    /**
     * account deleted or not
     */
    private Integer isDelete;
}