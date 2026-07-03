package com.example.service;

import com.example.dao.Pay_the_feesDao;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.example.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Pay_the_feesService {
    @Resource
    private Pay_the_feesDao pay_the_feesDao;

    public PageInfo<Pay_the_fees> findBySearch(Params params) {
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开启的分页设置来查询
        List<Pay_the_fees> list = pay_the_feesDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Pay_the_fees pay_the_fees) {
        System.out.println(pay_the_fees);
        pay_the_feesDao.insertSelective(pay_the_fees);
    }

    public Pay_the_fees find(String account) {
        return pay_the_feesDao.find(account);
    }

    public void delete() {
        pay_the_feesDao.del();
    }



}
