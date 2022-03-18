package com.mabrur.server.auth.controllers;

import com.mabrur.server.auth.dto.UserData;
import com.mabrur.server.auth.entities.User;
import com.mabrur.server.auth.services.UserService;
import com.mabrur.server.doa.dto.ResponseData;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<User>> register(@RequestBody UserData userData) {

        ResponseData<User> response = new ResponseData<>();
        User user = modelMapper.map(userData, User.class);
        response.setPayload(userService.registerUser(user));
        response.setStatus(true);
        response.getMessages().add("App User Saved !");
        return ResponseEntity.ok(response);

    }

}
