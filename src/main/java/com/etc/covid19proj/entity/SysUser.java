package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class SysUser {
    private Integer uid;
    private String username;
    private String password;
    private Integer rid;
    private Role role;

    public SysUser(Integer uid,Integer rid,String username,String password){
        this.rid=rid;
        this.uid=uid;
        this.username=username;
        this.password=password;
    }
}
