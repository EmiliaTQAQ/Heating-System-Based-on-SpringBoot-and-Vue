package com.example.service;

import com.example.dao.NoticeDao;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService {
    @Resource
    private NoticeDao noticeDao;

    public PageInfo<Notice> findBySearch(Params params) {
        //开启分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        //接下来的查询会自动按照当前开启的分页设置来查询
        List<Notice> list = noticeDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(Notice notice) {
        notice.setState("未发布");
        noticeDao.insertSelective(notice);
    }

    public void update(Notice notice) {
        noticeDao.updateByPrimaryKeySelective(notice);
    }

    public void delete(Integer id) {
        noticeDao.deleteByPrimaryKey(id);
    }

    public List<Notice> findAll(String state) {
        return noticeDao.findAll(state);
    }
}
