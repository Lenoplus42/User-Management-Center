package com.leno.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        String res = DigestUtils.md5DigestAsHex(("abcd").getBytes());
        System.out.println(res);
    }

    @Test
    void contextLoads() {
    }

}
