package com.example.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编译时注解
 */
@Retention(RetentionPolicy.CLASS)
/**
 * 该注解用于描述类、接口
 */
@Target(ElementType.TYPE)
public @interface BindPath {
    String value();     // 携带一个参数
}
