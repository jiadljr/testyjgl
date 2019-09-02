package com.qkby.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 导出excel注解 
 * 在成员变量上加该注解，表示需要导出 
 * name 列标题   
 * format 对date进行格式化 
 * cn.com.bizunited.base.utils.ExportUtil 进行解析并生成excel 
 * @author wish 
 * 
 */  
@Target ({ElementType.FIELD})  
@Retention (RetentionPolicy.RUNTIME)  
public @interface RowTitle {  
    public String name();  
    public String format() default "";  
} 
