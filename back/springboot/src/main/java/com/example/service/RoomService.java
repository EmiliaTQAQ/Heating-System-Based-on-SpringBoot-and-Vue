package com.example.service;

import com.example.dao.RoomDao;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Room;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomService {
    @Resource
    private RoomDao roomDao;

    public List<String> findAll() {
        return roomDao.selectCell();
    }

    public List<String>  findX(String x) {
        return roomDao.findX(x);
    }

    public  List<String> findY(String y) {
        return roomDao.findY(y);
    }

    public PageInfo<Room> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Room> list = roomDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void update(String heatingAccount, String heatingName,String cell) {
        roomDao.update(heatingAccount,heatingName,cell);
    }

    public PageInfo<Room> findBySearch1(Params params) {
        PageHelper.startPage(params.getPageNum(),params.getPageSize());
        List<Room> list = roomDao.findBySearch1(params);
        return PageInfo.of(list);
    }

    public void add(Room room) {
        room.setState("未缴费");
        Room room1 = roomDao.findGate(room.getCell(),room.getBuilding(),room.getGate());

        if (room1 != null) {
            //这里面些什么都不会在前端展示因为这是报错，但是controller规定报错不会停止，程序会继续运行
            throw new CustomException("有小区门牌号重复，请勿重复添加");
        }

        roomDao.insertSelective(room);
    }

    public void delete(Integer id) {
        roomDao.deleteByPrimaryKey(id);
    }

    public List<Room> find(String account) {
        return roomDao.find(account);
    }

    public String findAccount(String cell) {
        return roomDao.findAccount(cell);
    }


    public void up(String cell, String building, String gate) {
        roomDao.up(cell,building,gate);
    }

    public List<Room> findA() {
        return roomDao.findA();
    }

    public void hf() {
        roomDao.hf();
    }

    public void gx() {
        roomDao.gx();
    }
}
