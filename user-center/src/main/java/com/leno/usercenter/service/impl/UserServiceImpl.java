package com.leno.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leno.usercenter.model.domain.User;
import com.leno.usercenter.service.UserService;
import com.leno.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.leno.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author knox
* @description 针对表【user(stores user in UMC)】的数据库操作Service实现
* @createDate 2025-06-22 15:39:20
*/
@Service
@Slf4j // Allow you to use log in this class
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     * Salt Value, Obfuscate Password
     */
    private static final String SALT = "deepDark";

    @Override
    public long userRegister(String account, String password, String checkPassword) {
        // 1. User Verification
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            return -1;
        }

        if (account.length() < 4) {
            return -1;
        }

        if (password.length() < 8) {
            return -1;
        }

        // Account can't contain special syntax
        if (!account.matches("^[a-zA-Z0-9_-]+$")) {
            return -1;
        }

        // Password and verification password must match
        if (!password.equals(checkPassword)) {
            return -1;
        }

        // Account cannot duplicate
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // Encrypt Password
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        // Insert data into database
        User user = new User();
        user.setAccount(account);
        user.setPassword(encryptedPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String account, String password, HttpServletRequest request) {
        // 1. User Verification
        if (StringUtils.isAnyBlank(account, password)) {
            return null;
        }

        if (account.length() < 4) {
            return null;
        }

        if (password.length() < 8) {
            return null;
        }

        // Account can't contain special syntax
        if (!account.matches("^[a-zA-Z0-9_-]+$")) {
            return null;
        }

        // Encrypt Password
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());

        // Check User Existence
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("password", encryptedPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, account and password cannot match");
            return null;
        }

        // Desensitise
        User secureUser = getSecureUser(user);

        // Record user login session
        request.getSession().setAttribute(USER_LOGIN_STATE, secureUser); // Attribute in session is a map

        return secureUser;
    }

    @Override
    public User getSecureUser(User user) {
        User secureUser = new User();
        secureUser.setId(user.getId());
        secureUser.setUsername(user.getUsername());
        secureUser.setAccount(user.getAccount());
        secureUser.setAvatarUrl(user.getAvatarUrl());
        secureUser.setGender(user.getGender());
        secureUser.setPhone(user.getPhone());
        secureUser.setEmail(user.getEmail());
        secureUser.setStatus(user.getStatus());
        secureUser.setCreateTime(user.getCreateTime());
        secureUser.setIsDelete(0);
        return secureUser;
    }
}




