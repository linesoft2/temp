package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 海外输入病例对象
 */
@Data
@NoArgsConstructor
public class Overseasinput {
    private String datetime;
    private String provinceShortName;
    private Integer pid;
    private Integer confirmedCount;
    private String provinceName;
}
