package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Temperature;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TemperatureDao extends Mapper<Temperature> {
    @Select("select * from Temperature")
    List<Temperature> findBySearch(@Param("params") Params params);
    @Insert("INSERT INTO Temperature (account, name,address) SELECT account, name,address FROM pay_the_fees;")
    void in();
    @Update("UPDATE Temperature SET temperature = 25; ")
    void up();

    @Delete("delete from Temperature")
    void del();
}
