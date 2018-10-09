package com.sasya.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sasya.business.UserRegisterImpl;
import com.sasya.dto.SasyaResponse;
import com.sasya.dto.UserDto;
import com.sasya.models.User;
import com.sasya.repository.UserDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Inject
    private UserRegisterImpl registerImpl;

    @Inject
    private UserDAO userDAO;

    @Inject
    private BCryptPasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SasyaResponse register(@RequestBody String payload){
        JsonObject jsonObject = new Gson().fromJson(payload, JsonObject.class);
        String phone = jsonObject.get("phone").getAsString();
        registerImpl.registerUser(phone);
        SasyaResponse sasyaResponse = new SasyaResponse();
        sasyaResponse.setStatus("Success");
        sasyaResponse.setMessage("Registered Succesfully");
        return sasyaResponse;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        User saveUser = new User();
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDAO.save(saveUser);
    }

}
