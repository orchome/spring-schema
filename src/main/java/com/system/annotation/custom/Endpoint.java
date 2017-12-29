package com.system.annotation.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于暴露服务，通过在类上加入{@code @Endpoint}注解实现服务暴露的目的。
 * 扩展Spring的Bean扫描功能，在Bean上加入此注解后会自动注册到Spring容器中。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Endpoint {

    /**
     * 此Endpoint在Spring容器中的ID
     * @return
     */
    String id();

    /**
     * 服务发布的地址，服务器地址及端口号和项目路径
     * @return
     */
    String address();

}