package com.sasya.controllers;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.AddressDto;
import com.sasya.dto.LoginDto;
import com.sasya.dto.RegisterDto;
import com.sasya.dto.UserDto;
import com.sasya.response.SasyaResponse;
import com.sasya.service.CommonServiceImpl;
import com.sasya.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

/**
 * UserController
 */
@RestController
@RequestMapping(value = "v1/user")
@Api(value = "user", description = "User Profile")
public class UserController {

    @Inject
    private UserServiceImpl userService;

    @Inject
    private CommonServiceImpl commonService;

    private static final String SUFFIX = "/";

    /**
     * @param register
     * @return
     */
    @ApiOperation(value = "Register", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity register(@Valid @RequestBody RegisterDto register) {
        return userService.registerUser(register.getMobile());
    }

    /**
     * @param user
     * @return
     */
    @ApiOperation(value = "Add User", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SasyaConstants.CREATED, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity addUser(@Valid @RequestBody UserDto user) {
        return userService.addUser(user);
    }

    /**
     * @param login
     * @return
     */
    @ApiOperation(value = "login", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity loginUser(@Valid @RequestBody LoginDto login) {
        return userService.login(login);
    }


    /**
     * @param userId
     * @param address
     * @return
     */
    @ApiOperation(value = "Add Address", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SasyaConstants.CREATED, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/{user_id}/address/addAddress", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity addAddress(@PathVariable("user_id") BigDecimal userId, @Valid @RequestBody AddressDto address) {
        return userService.addAddress(address, userId);
    }


    @ApiOperation(value = "Delete Address", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/{user_id}/address/{address_id}/deleteAddress", method = RequestMethod.DELETE)
    public ResponseEntity deleteAddress(@PathVariable("user_id") BigDecimal userId, @PathVariable("address_id") BigDecimal addressId) {
        return userService.deleteAddress(userId, addressId);
    }


    /**
     * @param userId
     * @param addressId
     * @param address
     * @return
     */
    @ApiOperation(value = "Update Address", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/{user_id}/address/{address_id}/updateAddress", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity updateAddress(@PathVariable("user_id") BigDecimal userId,
                                        @PathVariable("address_id") BigDecimal addressId,
                                        @Valid @RequestBody AddressDto address) {
        return userService.updateAddress(userId, addressId, address);
    }

    /**
     * @return
     */
    @ApiOperation(value = "Categories", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/category/getAllCategory", method = RequestMethod.GET)
    public ResponseEntity getAllCategories() {
        return commonService.getAllCategory();
    }


    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @return
     */
    @ApiOperation(value = "Get Products", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public ResponseEntity getAllProducts(@RequestParam(value = "categoryId", required = false) BigDecimal categoryId,
                                         @RequestParam(value = "subCategoryId", required = false) BigDecimal subCategoryId,
                                         @RequestParam(value = "popularity", required = false) String popularity,
                                         @RequestParam(value = "id", required = false) List<BigDecimal> ids,
                                         @RequestParam(value = "filter", required = true) String filter){
        return commonService.getAllProducts(categoryId, subCategoryId, popularity,filter,ids);
    }

//    /**
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "Get Product", response = SasyaResponse.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
//            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
//            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
//    })
//    @RequestMapping(value = "/product/{id}/getProduct", method = RequestMethod.GET)
//    public ResponseEntity getProduct(@PathVariable("id") BigDecimal id) {
//        return commonService.getProduct(id);
//    }
//

    @ApiOperation(value="get Address",response= SasyaResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK",response=SasyaResponse.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code= 404,message="Address not found")
    })
    @RequestMapping(value = "/{user_id}/getAddress", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getAddress(@PathVariable("user_id") BigDecimal userId,  @RequestParam(value = "id",required = false) List<BigDecimal> addressIds,
                                     @RequestParam(value = "filter",required = true) String type){
        return userService.getAddress(userId, addressIds, type);

    }


}

