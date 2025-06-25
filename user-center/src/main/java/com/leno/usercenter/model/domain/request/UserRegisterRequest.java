package com.leno.usercenter.model.domain.request;


import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author knox
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String account;
    private String password;
    private String checkPassword;

}
