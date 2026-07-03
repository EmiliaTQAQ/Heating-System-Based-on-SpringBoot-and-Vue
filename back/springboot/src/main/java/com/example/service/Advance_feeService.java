package com.example.service;

import com.example.dao.Advance_feeDao;
import com.example.dao.Pay_the_feesDao;
import com.example.entity.Advance_fee;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Advance_feeService {
    @Resource
    private Advance_feeDao advance_feeDao;

    public PageInfo<Advance_fee> findBySearch(Params params) {
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开启的分页设置来查询
        List<Advance_fee> list = advance_feeDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Advance_fee advance_fee) {
        advance_feeDao.insertSelective(advance_fee);
    }

    public Advance_fee find(String account) {
        return advance_feeDao.find(account);
    }

    public void delete() {
        advance_feeDao.del();
    }

    public void transfer() {
        advance_feeDao.transfer();
    }
}
