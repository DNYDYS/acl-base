package com.tx.base.security.security;

import com.tx.base.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码处理配置类
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    /**
     * 无参构造方法
     */
    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * 1个参数构造方法
     * @param strength
     */
    public DefaultPasswordEncoder(int strength) {
    }

    /**
     * 进行MD5加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        //使用工具类加密
        return MD5.encrypt(charSequence.toString());
    }

    /**
     * 进行密码比对
     * @param charSequence
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(charSequence.toString()));
    }
}
