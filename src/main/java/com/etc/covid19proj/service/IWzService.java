package com.etc.covid19proj.service;

import com.etc.covid19proj.entity.Wz;

import java.util.List;

public interface IWzService {
    public List<Wz> findByConditions(Wz wzParam);

    public Wz findByName(String name);

    public boolean addWz(Wz wzParam);

    public boolean updateWz(Wz wzParam);
}
