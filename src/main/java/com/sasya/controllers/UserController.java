package com.sasya.controllers;

import com.sasya.dto.LoginDto;
import com.sasya.dto.RegisterDto;
import com.sasya.dto.UserDto;
import com.sasya.exception.SasyaException;
import com.sasya.service.UserServiceImpl;
import com.sasya.response.SasyaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sasya.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * UserController
 */
@RestController
@RequestMapping(value="v1/user",consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
@Api(value="user",description="User Profile",produces ="application/json")
public class UserController {

    @Inject
    private UserServiceImpl userService;

    /**
     * @param register
     * @return
     */
    @ApiOperation(value="Register",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ok",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Bad Request")
    })

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterDto register){
        return userService.registerUser(register.getMobile());
    }

    /**
     * @param user
     * @return
     */
    @ApiOperation(value="Add User",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="User added Successfully",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Bad Request")
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    /**
     * @param login
     * @return
     */
    @ApiOperation(value="login",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Ok",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Bad Request")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody LoginDto login){
        return userService.login(login);
    }

}
