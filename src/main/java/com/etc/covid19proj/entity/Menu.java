package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Menu {
    private Integer mid;
    private String mname;
    private String murl;
    private Integer pmid;
    private Integer level;
    private Integer sort;
    private String identification;
}
