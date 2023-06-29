package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wz {
    private String name;
    private Integer cg;
    private Integer xb;
    private Integer jz;
    private Integer xh;
    private Integer xq;
    private Integer kc;
    private String srcName;//保存更新过程中原有的主键name字段值
}
