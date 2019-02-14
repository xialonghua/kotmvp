package com.lhxia.kotmvp.core;


import java.lang.annotation.*;

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
@Target({ElementType.TYPE})    //表示注解的作用对象，ElementType.TYPE表示类，ElementType.METHOD表示方法
@Retention(RetentionPolicy.RUNTIME)        //表示注解的保留机制，RetentionPolicy.RUNTIME表示运行时注解
@Inherited            //表示该注解可继承
public @interface Repo {

    java.lang.Class implClass();
}
