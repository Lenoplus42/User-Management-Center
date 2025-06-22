package com.leno.usercenter.service;
import java.util.Date;

import com.leno.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User Service Test
 *
 * @author knox
 * */

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User(); // Use generateAllSetter plugin

        user.setUsername("DeepDark");
        user.setAccount("123");
        user.setAvatarUrl("https://i1.hdslb.com/bfs/garb/item/488870931b1bba66da36d22848f0720480d3d79a.png@360w_360h_!web-avatar-nav.avif");
        user.setGender(0);
        user.setPassword("123456");
        user.setPhone("123");
        user.setEmail("123");
        user.setStatus(0);
        user.setIsDelete(0);

        boolean res = userService.save(user);
        System.out.println(user.getId());

        assertTrue(res);
    }
}