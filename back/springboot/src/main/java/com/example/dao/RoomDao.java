package com.example.dao;

import com.example.entity.Params;
import com.example.entity.Room;
import com.example.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoomDao extends Mapper<Room> {
    @Select("select distinct  cell  from room")
    List<String> selectCell();
    @Select("select distinct  building  from room where cell = #{cell}")
    List<String> findX(@Param("cell") String x);
    @Select("select distinct  gate  from room where building = #{building}")
    List<String> findY(@Param("building") String y);
    @Select("select cell,ANY_VALUE(heating_account) AS heating_account,ANY_VALUE(heating_name) AS heating_name  from room GROUP BY cell")
    List<Room> findBySearch(@Param("params") Params params);
    @Update("update room set  heating_account = #{heating_account}, heating_name = #{heating_name} where cell = #{cell}")
    void update(@Param("heating_account") String heatingAccount,@Param("heating_name") String heatingName,@Param("cell") String cell);
    @Select("select * from room")
    List<Room> findBySearch1(@Param("params") Params params);
    @Select("select * from room where  cell = #{cell} and building = #{building} and gate = #{gate}")
    Room findGate(@Param("cell") String cell,@Param("building") String building,@Param("gate") String gate);
    @Select("select * from room where heating_account = #{heating_account}")
    List<Room> find(@Param("heating_account") String heating_account);
    @Select("select distinct  heating_account  from room where cell = #{cell} limit 1")
    String findAccount(@Param("cell") String cell);

    @Update("update room set state = '已缴费' where cell = #{cell} and building = #{building} and gate = #{gate}")
    void up(@Param("cell") String cell,@Param("building")  String building,@Param("gate")  String gate);

    @Select("select * from room")
    List<Room> findA();
    @Select("select count(*) from room where  cell = #{cell} and building = #{building} and gate = #{gate}")
    Integer f(@Param("cell") String cell,@Param("building")  String building,@Param("gate")  String gate);
    @Update("update room set state = '未缴费'")
    void hf();
    @Update("UPDATE room JOIN pay_the_fees ON CONCAT(room.cell,'小区', room.building, '号楼',room.gate) = pay_the_fees.address SET room.state = '已缴费' ")
    void gx();
}
