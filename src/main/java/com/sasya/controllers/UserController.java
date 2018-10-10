package com.sasya.controllers;

import com.sasya.dto.Register;
import com.sasya.response.SasyaResponse;
import com.sasya.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

@RestController
@Api(value="/customer",description="Customer Profile",produces ="application/json")
@RequestMapping(value="/v1/user",consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
public class UserController {

    @Inject
    private UserServiceImpl registerImpl;

    @ApiOperation(value="get customer",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Registerd Successfully",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Bad Request")
    })

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SasyaResponse register(@RequestBody Register register){
        registerImpl.registerUser(register.getMobile());
        SasyaResponse sasyaResponse = new SasyaResponse("Success","Registered Succesfully");
        return sasyaResponse;
    }

}
