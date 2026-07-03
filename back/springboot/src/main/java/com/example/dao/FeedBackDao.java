package com.example.dao;

import com.example.entity.FeedBack;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FeedBackDao extends Mapper<FeedBack> {
    List<FeedBack> findBySearch(@Param("params") Params params);
    @Select("select * from feedback where account = #{params.account}")
    List<FeedBack> findBySearchF(@Param("params") Params params);
}
