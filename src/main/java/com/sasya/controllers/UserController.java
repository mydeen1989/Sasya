package com.sasya.controllers;

import com.sasya.dto.LoginDto;
import com.sasya.dto.RegisterDto;
import com.sasya.dto.UserDto;
import com.sasya.service.UserServiceImpl;
import com.sasya.response.SasyaResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value="/user",consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
public class UserController {

    @Inject
    private UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SasyaResponse register(@RequestBody RegisterDto register){
        userService.registerUser(register.getMobile());
        SasyaResponse sasyaResponse = new SasyaResponse("Success","Registered Succesfully");
        return sasyaResponse;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public SasyaResponse addUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public SasyaResponse loginUser(@RequestBody LoginDto login){
        return userService.login(login);
    }
}
