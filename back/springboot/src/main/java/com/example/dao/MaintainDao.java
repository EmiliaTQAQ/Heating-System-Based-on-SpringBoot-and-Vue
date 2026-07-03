package com.example.dao;

import com.example.entity.Maintain;
import com.example.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MaintainDao extends Mapper<Maintain> {
    List<Maintain> findBySearch(@Param("params") Params params);

    @Select("select * from maintain where   account = #{params.account}")
    List<Maintain> findBySearchU(@Param("params") Params params);

    List<Maintain> findBySearchH(@Param("params") Params params);
}
