package com.leno.usercenter.service;
import java.util.Date;

import com.leno.usercenter.service.UserService;
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

    @Test
    void userRegister() {
        String account = "leno";
        String password = "";
        String checkPassword = "12345678";
        long res = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, res);
        account = "le";
        res = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, res);
        account = "le no";
        res = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, res);
        password = "12345678";
        checkPassword = "123456789";
        res = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, res);
        account = "lenoP"; // Will create a new account in database.
        password = "12345678";
        checkPassword = "12345678";
        res = userService.userRegister(account, password, checkPassword);
        Assertions.assertTrue(res > 0);
    }
}