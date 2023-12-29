package com.example.myapp.controller;

import com.example.myapp.model.UsersDto;
import com.example.myapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/models/{id}")
    public ResponseEntity<List<UsersDto>> getAllModels(@PathVariable("id") int id) {
        List<UsersDto> users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }
}