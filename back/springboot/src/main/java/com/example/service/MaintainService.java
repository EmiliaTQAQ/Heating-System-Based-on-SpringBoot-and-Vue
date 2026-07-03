package com.example.service;

import com.example.dao.MaintainDao;
import com.example.entity.Accessories;
import com.example.entity.Maintain;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaintainService {

    @Resource
    private MaintainDao maintainDao;
    public PageInfo<Maintain> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Maintain> list = maintainDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        maintainDao.deleteByPrimaryKey(id);
    }

    public void add(Maintain maintain) {
        maintainDao.insertSelective(maintain);
    }

    public void update(Maintain maintain) {
        maintainDao.updateByPrimaryKeySelective(maintain);
    }

    public PageInfo<Maintain> findBySearchU(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Maintain> list = maintainDao.findBySearchU(params);
        return PageInfo.of(list);
    }
    public PageInfo<Maintain> findBySearchH(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Maintain> list = maintainDao.findBySearchH(params);
        return PageInfo.of(list);
    }
}
