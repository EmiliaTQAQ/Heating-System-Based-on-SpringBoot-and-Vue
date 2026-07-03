package com.example.service;

import com.example.dao.AccessoriesDao;
import com.example.entity.Accessories;
import com.example.entity.FeedBack;
import com.example.entity.Params;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccessoriesService {
    @Resource
    private AccessoriesDao accessoriesDao;

    public PageInfo<Accessories> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Accessories> list = accessoriesDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Accessories accessories) {
        Accessories accessories1 = accessoriesDao.find(accessories.getAccessories());
        if (accessories1 != null) {
            throw new CustomException("配件重复");
        }
        accessoriesDao.insertSelective(accessories);
    }

    public void update(Accessories accessories) {

        accessoriesDao.updateByPrimaryKeySelective(accessories);
    }

    public void delete(Integer id) {
        accessoriesDao.deleteByPrimaryKey(id);
    }
}
