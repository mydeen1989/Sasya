package com.sasya.controllers;


import com.sasya.response.SasyaResponse;
import com.sasya.service.AdminServiceImpl;
import com.sasya.service.CommonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@RequestMapping(value="v1/admin")
@Api(value="admin",description="Admin",produces ="application/json")
public class AdminController {

     @Inject
    private AdminServiceImpl adminService;


    @Inject
    private CommonServiceImpl commonService;


    @ApiOperation(value="Add Category",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestPart(value = "file") MultipartFile file,
                                      @RequestParam("categoryName") String categoryName) {
        return adminService.addCategory(file,categoryName);
    }


    @ApiOperation(value="Remove Category",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })
    @RequestMapping(value = "/removeCategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("id") BigDecimal id) {
        return adminService.deleteCategory(id);
    }


    @ApiOperation(value="Update Category",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=400,message="Invalid Request")
    })
    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@PathVariable("id") BigDecimal id, @RequestPart(value = "file")
            MultipartFile file, @RequestParam("categoryName") String categoryName) {
        return adminService.updateCategory(id, file, categoryName);
    }

    @ApiOperation(value="Categories",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code= 404,message="Address not found")
    })
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
    public ResponseEntity getAllCategories(){
        return commonService.getAllCategory();
    }

}
