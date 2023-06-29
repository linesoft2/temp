package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Province {
    private String datetime;
    private Integer locationId;
    private String provinceShortName;
    private String cityName;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private String provinceName;
}
