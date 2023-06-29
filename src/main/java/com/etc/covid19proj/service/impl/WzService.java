package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Wz;
import com.etc.covid19proj.mapper.WzMapper;
import com.etc.covid19proj.service.IWzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class WzService implements IWzService {
    @Resource
    private WzMapper wzMapper;
    @Override
    public List<Wz> findByConditions(Wz wzParam) {
        return wzMapper.selectByConditions(wzParam);
    }

    @Override
    public Wz findByName(String name) {
        return wzMapper.selectByName(name);
    }

    @Override
    public boolean addWz(Wz wzParam) {
        //对数据库进行验证，判断新添加的记录是否和原有记录存在不完整性
        //判断新的记录的主键是否在表中重复
        Wz wz=wzMapper.selectByName(wzParam.getName());
        //新的记录的主键值是唯一的
        if (wz==null){
            wzMapper.insertWz(wzParam);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateWz(Wz wzParam) {
        //对数据库进行验证，判断新添加的记录是否和原有记录存在不完整性
        //判断新的记录的主键是否在表中
        Wz wz=wzMapper.selectByName(wzParam.getSrcName());
        //待更新的记录在表中存在
        if (wz!=null){
            wzMapper.updateWz(wzParam);
            return true;
        }
        return false;
    }
}
