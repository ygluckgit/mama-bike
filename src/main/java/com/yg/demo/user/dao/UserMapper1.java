//package com.yg.demo.user.dao;
//
//import com.yg.demo.user.entity.User;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//@Mapper
//public interface UserMapper1 {
//    @Delete("delete *from user where id=#{id}")
//    int deleteByPrimaryKey(Integer id);
//    @Select("select username from user where id=#{id}" )
//    String selectById(int id);
//
//}