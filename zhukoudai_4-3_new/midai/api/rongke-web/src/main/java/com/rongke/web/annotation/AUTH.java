package com.rongke.web.annotation;

import com.rongke.enums.LogR;

import java.lang.annotation.*;

/**
 * 登录效验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AUTH {
    String value() default "";
    int type() default 0;
    LogR result() default LogR.RESULT_NOT_BC;
}
