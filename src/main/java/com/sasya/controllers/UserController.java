package com.sasya.controllers;

import com.sasya.dto.AddressDto;
import com.sasya.dto.LoginDto;
import com.sasya.dto.RegisterDto;
import com.sasya.dto.UserDto;
import com.sasya.exception.SasyaException;
import com.sasya.service.UserServiceImpl;
import com.sasya.response.SasyaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sasya.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 * UserController
 */
@RestController
@RequestMapping(value="v1/user")
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
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity register(@Valid @RequestBody RegisterDto register){
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
            @ApiResponse(code=400,message="Invalid Request")
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST ,consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity addUser(@Valid @RequestBody UserDto user){
        return userService.addUser(user);
    }

    /**
     * @param login
     * @return
     */
    @ApiOperation(value="login",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST ,consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity loginUser(@Valid @RequestBody LoginDto login){
        return userService.login(login);
    }


    @ApiOperation(value="addAddress",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })
    @RequestMapping(value = "/{user_id}/addAddress", method = RequestMethod.POST ,consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity addAddress(@PathVariable("user_id") BigDecimal userId,@Valid @RequestBody AddressDto address){
        return userService.addAddress(address, userId);
    }


    @ApiOperation(value="deleteAddress",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code= 404,message="Address not found")
    })
    @RequestMapping(value = "/{user_id}/deleteAddress/{address_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAddress(@PathVariable("user_id") BigDecimal userId,@PathVariable("address_id") BigDecimal addressId){
        return userService.deleteAddress(userId,addressId);
    }


    @ApiOperation(value="update Address",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code= 404,message="Address not found")
    })
    @RequestMapping(value = "/{user_id}/updateAddress", method = RequestMethod.PUT ,consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_JSON})
     public ResponseEntity updateAddress(@PathVariable("user_id") BigDecimal userId,@Valid @RequestBody AddressDto address){
        return userService.updateAddress(userId,address);
    }

}

