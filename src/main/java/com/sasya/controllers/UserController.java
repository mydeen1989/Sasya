package com.sasya.controllers;

import com.sasya.dto.Register;
import com.sasya.service.UserServiceImpl;
import com.sasya.response.SasyaResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value="/v1/user",consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
public class UserController {

    @Inject
    private UserServiceImpl registerImpl;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SasyaResponse register(@RequestBody Register register){
        registerImpl.registerUser(register.getMobile());
        SasyaResponse sasyaResponse = new SasyaResponse("Success","Registered Succesfully");
        return sasyaResponse;
    }



}
