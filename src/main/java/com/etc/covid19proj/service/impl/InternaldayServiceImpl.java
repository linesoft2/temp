package com.etc.covid19proj.service.impl;

import com.etc.covid19proj.entity.Internalday;
import com.etc.covid19proj.mapper.InternaldayMapper;
import com.etc.covid19proj.service.IInternaldayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class InternaldayServiceImpl implements IInternaldayService {

    @Resource
    private InternaldayMapper internaldayMapper;
    @Override
    public List<Internalday> findByConditions(Internalday internaldayParam) {
        return internaldayMapper.selectByConditions(internaldayParam);
    }

    @Override
    public Internalday findByDateId(String dateId) {
        return internaldayMapper.selectByDateId(dateId);
    }
}
