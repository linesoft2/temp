package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Overseasinput;

import java.util.List;

public interface IOverseasinputService {
    public List<Overseasinput> findOverseasinputByConditions(Overseasinput overseasinputParam);

    public List<String> findDistinctProvinceName();

    public  boolean addOverseasinput(Overseasinput overseasinputParam);

    public void updateOverseasinput(Overseasinput overseasinputParam);

    public Overseasinput findByPidAndDatetime(Integer pid,String datetime);
}
