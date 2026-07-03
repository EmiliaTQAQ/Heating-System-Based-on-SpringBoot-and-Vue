package com.example.dao;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AdminDao extends Mapper<Admin> {
    @Select("select * from admin where account = #{account} and password = #{password} and role = #{role} limit 1")
    Admin findByNameAndPassword(@Param("account") String account, @Param("password") String password, @Param("role") String role);

    @Select("select * from admin where id = #{id}")
    Admin findById(@Param("id") Integer id);
}
