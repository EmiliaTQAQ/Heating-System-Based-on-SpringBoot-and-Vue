package com.example.dao;


import com.example.entity.Heating_engineer;
import com.example.entity.Params;
import com.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface Heating_engineerDao extends Mapper<Heating_engineer> {
    @Select("select * from heating_engineer where account = #{account} and password = #{password} and role = #{role} limit 1")
    Heating_engineer findByNameAndPassword(@Param("account") String account, @Param("password") String password, @Param("role") String role);


    List<Heating_engineer> findBySearch(@Param("params") Params params);
    @Select("select * from heating_engineer where id = #{id} ")
    Heating_engineer findById(@Param("id") Integer id);
    @Select("select * from heating_engineer where account = #{account} limit 1")
    Heating_engineer findByAccount(@Param("account") String account);
    @Select("select account from heating_engineer ")
    List<String> findByHeating();
    @Select("select name from heating_engineer where account = #{account}")
    String findName(@Param("account") String value);
}
