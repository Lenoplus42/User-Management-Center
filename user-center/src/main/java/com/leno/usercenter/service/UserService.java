package com.leno.usercenter.service;

import com.leno.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author knox
 * @description Business Logic
 * @createDate 2025-06-22 15:39:20
 */
public interface UserService extends IService<User> {

    /**
     * User Register
     *
     * @param account
     * @param password
     * @param checkPassword
     * @return new user id
     */
    long userRegister(String account, String password, String checkPassword);

    /**
     * User Login
     *
     * @param account
     * @param password
     * @param request
     * @return Desensitized User Info
     */
    User userLogin(String account, String password, HttpServletRequest request);

    /**
     * Desensitise users
     *
     * @param user
     * @return
     */
    User getSecureUser(User user);
}
