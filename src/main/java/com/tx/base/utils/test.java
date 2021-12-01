package com.tx.base.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: --
 * @author：Bing
 * @date：2021/12/1 16:51
 * @version：1.0
 */
public class test {

    public static void main(String[] args) {
        BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
        String encode = bp.encode("123456");
        System.out.println(encode);

    }
}
