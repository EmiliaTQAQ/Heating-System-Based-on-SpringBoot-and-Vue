package com.example.dao;


import com.example.entity.Params;
import com.example.entity.Room;
import com.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {
    @Select("select * from user where account = #{account} and password = #{password} and role = #{role}  limit 1")
    User findByNameAndPassword(@Param("account") String account, @Param("password") String password, @Param("role") String role);
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    List<User> findBySearch(@Param("params") Params params);
    @Select("select * from user where account = #{account} limit 1")
    User findByAccount(@Param("account") String account);
    @Select("select * from user")
    List<User> find();
    @Select("select count(*) from user where address = #{address}")
    boolean findAddress(@Param("address") String address);

    @Update("update user set balance = #{balance} where account = #{account}")
    void upB(@Param("account") String account,@Param("balance")  Double balance);
    @Select("select balance from user where  id = #{id}")
    Integer findBalance(@Param("id") Integer id);
    @Select("select  u.account from user u LEFT JOIN pay_the_fees p ON u.account = p.account where p.account IS NULL AND u.account = #{account}")
    String tz(@Param("account") String account);
}
