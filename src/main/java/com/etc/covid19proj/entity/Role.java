package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Role {
    //Integer是java中基本类型int的封装类，整型数据对象
    //在java中有一种对象和基本数据类型相互转换的机制叫封箱拆箱
    //封箱将基本数据类型转为对象，拆箱是封箱的逆操作
    //因为在Java应用中所有的操作和数据都是以对象形式存在，null是所有对象都有的值
    //为了保证整型类型的字段也有null可以进行判断，使用Integer
    private Integer rid;
    private String rname;
    private String rdesc;
}
