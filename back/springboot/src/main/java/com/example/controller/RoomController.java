package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Notice;
import com.example.entity.Params;
import com.example.entity.Room;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.RoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Room> info = roomService.findBySearch(params);
        return Result.success(info);
    }
    @GetMapping("/search1")
    public Result findBySearch1(Params params) {
        PageInfo<Room> info = roomService.findBySearch1(params);
        return Result.success(info);
    }
    @GetMapping
    public Result findAll() {
        return Result.success(roomService.findAll());
    }
    @GetMapping("/x/{x}")
    public Result findX(@PathVariable String x) {
        return Result.success(roomService.findX(x));
    }
    @GetMapping("/y/{y}")
    public Result findY(@PathVariable String y) {
        return Result.success(roomService.findY(y));
    }
    @GetMapping("/findAccount/{cell}")
    public Result findAccount(@PathVariable String cell) {
        return Result.success(roomService.findAccount(cell));
    }
    @GetMapping("/up/{heating_account}/{heating_name}/{cell}")
    public Result save(@PathVariable String heating_account,@PathVariable String heating_name,@PathVariable String cell){
        System.out.println(cell);
        roomService.update(heating_account,heating_name,cell);
        return Result.success();
    }
    //上传Excel到表格
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String x = null;
        List<Room> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Room.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (Room room : infoList) {
                try {
                    roomService.add(room);
                } catch (Exception e) {
                    //如果导入数据的时候出现异常，就会报出异常，但是不影响剩下的数据导入
                    e.printStackTrace();
                    x = "有小区门牌号重复，请勿重复添加";
                }
            }
        }
        return Result.success(x);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        roomService.delete(id);
        return Result.success();
    }

    @GetMapping("/echarts/bie/{account}")
    public Result bie(@PathVariable String account) {
        List<Room> list = roomService.find(account);
        Map<String,Long> collect = list.stream().
                filter(x -> ObjectUtil.isNotEmpty(x.getCell()))
                .collect(Collectors.groupingBy(Room::getCell,Collectors.counting()));
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",key);
                map.put("value",collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }
    @GetMapping("/u/{cell}/{building}/{gate}")
    public Result save1(@PathVariable String cell,@PathVariable String building,@PathVariable String gate) {
        roomService.up(cell,building,gate);
        return Result.success();
    }
    @GetMapping("/echarts/bie1")
    public Result bie1() {
        List<Room> list = roomService.findA();
        Map<String,Long> collect = list.stream().
                filter(x -> ObjectUtil.isNotEmpty(x.getState()))
                .collect(Collectors.groupingBy(Room::getState,Collectors.counting()));
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                Map<String,Object> map = new HashMap<>();
                map.put("name",key);
                map.put("value",collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @PostMapping("/hf")
    public Result hf(){
        roomService.hf();
        return Result.success();
    }
    @PostMapping("/gx")
    public Result gx(){
        roomService.gx();
        return Result.success();
    }

}
