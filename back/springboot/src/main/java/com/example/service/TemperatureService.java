package com.example.service;

import com.example.dao.Pay_the_feesDao;
import com.example.dao.TemperatureDao;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.example.entity.Room;
import com.example.entity.Temperature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemperatureService {

    @Resource
    private TemperatureDao temperatureDao;
    @Resource
    private Pay_the_feesDao pay_the_feesDao;

    public PageInfo<Temperature> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Temperature> list = temperatureDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void update(Temperature temperature) {
        temperatureDao.insertSelective(temperature);
    }

    public void save1() {
        temperatureDao.in();
        temperatureDao.up();
    }

    public void del() {
        temperatureDao.del();
    }
}
