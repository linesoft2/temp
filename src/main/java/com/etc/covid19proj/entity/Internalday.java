package com.etc.covid19proj.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 疫情历史数据实体对象
 *
 */
@Data
@NoArgsConstructor
public class Internalday {
    private String dateId;
    private Integer confirmedIncr;
    private Integer confirmedCount;
    private Integer suspectedCount;
    private Integer curedCount;
    private Integer deadCount;
    private String startDate;
    private String endDate;
}
