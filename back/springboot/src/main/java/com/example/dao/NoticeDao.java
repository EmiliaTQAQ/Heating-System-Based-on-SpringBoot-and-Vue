package com.example.dao;

import com.example.entity.Notice;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NoticeDao extends Mapper<Notice> {
    @Select("select * from notice")
    List<Notice> findBySearch(@Param("params") Params params);

    @Select("select * from notice where state = #{state}")
    List<Notice> findAll(@Param("state") String state);
}
