package com.leno.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leno.usercenter.model.User;

public interface UserMapper extends BaseMapper<User> {
    // Inherit from mybatis plus BaseMapper. My batis is a database action tool, mybatis plus provides all CRUD actions for us
    // Just press CTRL and clink BaseMapper to see the actions
}
