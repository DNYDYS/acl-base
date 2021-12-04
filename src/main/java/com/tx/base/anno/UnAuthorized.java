package com.tx.base.anno;

import java.lang.annotation.*;

/**
 * @description: --自定义注解，加此注解的方法，不需要验证授权。用到controller的方法上
 * @author：Bing
 * @date：2021/12/2 18:05
 * @version：1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UnAuthorized {
    boolean required();
}
