package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface Pay_the_feesDao extends Mapper<Pay_the_fees> {
    List<Pay_the_fees> findBySearch(@Param("params") Params params);
    @Select("select * from pay_the_fees where account = #{account}")
    Pay_the_fees find(@Param("account") String account);
    @Delete("delete  from pay_the_fees")
    void del();
}
