package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Overseasinput;
import com.etc.covid19proj.mapper.OverseasinputMapper;
import com.etc.covid19proj.service.IOverseasinputService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class OverseasinputService implements IOverseasinputService {

    @Resource
    private OverseasinputMapper overseasinputMapper;
    @Override
    public List<Overseasinput> findOverseasinputByConditions(Overseasinput overseasinputParam) {
        return overseasinputMapper.selectOverseasinputByConditions(overseasinputParam);
    }

    @Override
    public List<String> findDistinctProvinceName() {
        return overseasinputMapper.selectDistinctProvinceName();
    }

    @Override
    public boolean addOverseasinput(Overseasinput overseasinputParam) {
        overseasinputParam.setPid(Integer.parseInt(overseasinputParam.getProvinceName()
                .split(",")[0]));
        overseasinputParam.setProvinceShortName(overseasinputParam.getProvinceName()
                .split(",")[1]);
        if (overseasinputMapper.selectByPidAndDatetime(overseasinputParam.getPid()
        ,overseasinputParam.getDatetime())==null){
            overseasinputMapper.insertOverseasinput(overseasinputParam);
            return true;
        }
        return false;
    }

    @Override
    public void updateOverseasinput(Overseasinput overseasinputParam) {
        if (overseasinputMapper.selectByPidAndDatetime(overseasinputParam.getPid()
                ,overseasinputParam.getDatetime())!=null){
            overseasinputMapper.updateOverseasinput(overseasinputParam);
        }
    }

    @Override
    public Overseasinput findByPidAndDatetime(Integer pid, String datetime) {
        return overseasinputMapper.selectByPidAndDatetime(pid,datetime);
    }
}
