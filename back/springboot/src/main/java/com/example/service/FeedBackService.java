package com.example.service;

import com.example.dao.FeedBackDao;
import com.example.entity.FeedBack;
import com.example.entity.Params;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FeedBackService {
    @Resource
    private FeedBackDao feedBackDao;
    public PageInfo<FeedBack> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<FeedBack> list = feedBackDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(FeedBack feedBack) {
        feedBack.setState("未处理");
        System.out.println(feedBack.getContent());
        if (feedBack.getContent() != null)
        {
            feedBackDao.insertSelective(feedBack);
        } else {
            throw new CustomException("反馈内容不能为空");
        }

    }

    public void delete(Integer id) {
        feedBackDao.deleteByPrimaryKey(id);
    }

    public void update(FeedBack feedback) {
        feedBackDao.updateByPrimaryKeySelective(feedback);
    }

    public PageInfo<FeedBack> findBySearchF(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<FeedBack> list = feedBackDao.findBySearchF(params);
        return PageInfo.of(list);
    }
}
