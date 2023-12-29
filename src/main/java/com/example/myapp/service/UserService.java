package com.example.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.myapp.repository.UserMapper;
import com.example.myapp.model.UsersDto;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    public List<UsersDto> getUserById(int id) {
        List<UsersDto> list = userMapper.selectMyModel(id);
        return list;
    }
}