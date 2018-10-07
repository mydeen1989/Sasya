package com.sasya.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sasya.business.UserRegisterImpl;
import com.sasya.dto.SasyaResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/v1/user")
public class UserService {

    @Inject
    private UserRegisterImpl registerImpl;

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

}
