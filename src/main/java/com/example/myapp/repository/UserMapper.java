package com.example.myapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.myapp.model.UsersDto;

@Mapper
public interface UserMapper  {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<UsersDto> selectMyModel(int id);
}