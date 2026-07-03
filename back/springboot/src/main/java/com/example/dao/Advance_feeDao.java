package com.example.dao;

import com.example.entity.Advance_fee;
import com.example.entity.Params;
import com.example.entity.Pay_the_fees;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface Advance_feeDao extends Mapper<Advance_fee> {
    List<Advance_fee> findBySearch(@Param("params") Params params);
    @Select("select * from advance_fee where account = #{account}")
    Advance_fee find(@Param("account") String account);
    @Delete("delete  from advance_fee")
    void del();
    @Insert("INSERT INTO pay_the_fees SELECT * FROM advance_fee;")
    void transfer();
}
