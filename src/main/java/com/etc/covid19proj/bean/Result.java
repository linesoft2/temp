package com.etc.covid19proj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //响应给前台页面的数据
    private Object data;
    //响应代码，整数
    private Integer code;
    //响应消息
    private String message;

    /**
     * 响应成功
     * @param data 返回的数据
     * @return 接口对象
     */
    public static Result success(Object data){
        Result result=new Result();
        result.code=200;
        result.message="success";
        result.data=data;
        return result;
    }

    /**
     * 响应失败
     * @return 接口对象
     */
    public static Result fail(){
        Result result=new Result();
        result.code=500;
        result.message="fail";
        result.data=null;
        return result;
    }
}
