package com.leno.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leno.usercenter.model.domain.User;
import com.leno.usercenter.service.UserService;
import com.leno.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author knox
* @description 针对表【user(stores user in UMC)】的数据库操作Service实现
* @createDate 2025-06-22 15:39:20
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




