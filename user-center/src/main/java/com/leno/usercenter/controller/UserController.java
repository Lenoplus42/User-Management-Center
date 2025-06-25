package com.leno.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leno.usercenter.constant.UserConstant;
import com.leno.usercenter.model.domain.User;
import com.leno.usercenter.model.domain.request.UserLoginRequest;
import com.leno.usercenter.model.domain.request.UserRegisterRequest;
import com.leno.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.leno.usercenter.constant.UserConstant.ADMIN_ROLE;

/**
 * User Interface for frontend
 *
 * @author knox
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            return null;
        }
        return userService.userRegister(account, password, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(account, password)) {
            return null;
        }
        return userService.userLogin(account, password, request);
    }

    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username",  username);
        }
        List<User> users = userService.list(queryWrapper);
        return users.stream().peek(user -> userService.getSecureUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }

        return userService.removeById(id); // Logic Delete
    }

    /**
     * User Role Authentication
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // Authentication
        Object userObject = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObject;
        return user != null && ADMIN_ROLE == user.getRole();

    }
}
