package com.sasya.controllers;


import com.sasya.constant.SasyaConstants;
import com.sasya.dto.ProductDto;
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
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/admin")
@Api(value = "admin", description = "Admin", produces = "application/json")
public class AdminController {

    @Inject
    private AdminServiceImpl adminService;


    @Inject
    private CommonServiceImpl commonService;


    /**
     * @param file
     * @param categoryName
     * @return
     */
    @ApiOperation(value = "Add Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", response = SasyaResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Invalid Request")
    })

    @RequestMapping(value = "/category/addCategory", method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestPart(value = "file",required = false) MultipartFile file,
                                      @RequestParam("categoryName") String categoryName) {
        return adminService.addCategory(file, categoryName);
    }


    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "Remove Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Invalid Request")
    })
    @RequestMapping(value = "/category/{id}/removeCategory", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("id") BigDecimal id) {
        return adminService.deleteCategory(id);
    }


    /**
     * @param id
     * @param file
     * @param categoryName
     * @return
     */
    @ApiOperation(value = "Update Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SasyaResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Invalid Request")
    })
    @RequestMapping(value = "/category/{id}/updateCategory", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@PathVariable("id") BigDecimal id, @RequestPart(value = "file")
            MultipartFile file, @RequestParam(value = "categoryName", required = false) String categoryName) {
        return adminService.updateCategory(id, file, categoryName);
    }

    /**
     * @return
     */
    @ApiOperation(value = "Categories",response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/category/getAllCategory", method = RequestMethod.GET)
    public ResponseEntity getAllCategories(@RequestParam(value = "name", required = false) List<String> categoryNames) {
        return commonService.getAllCategory(categoryNames);
    }


    /**
     * @param productDto
     * @return
     */
    @ApiOperation(value = "Add Product", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SasyaConstants.CREATED, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })

    @RequestMapping(value = "/product/addProduct", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {
        return adminService.addProduct(productDto);
    }


    /**
     * @param file
     * @param id
     * @return
     */
    @ApiOperation(value = "Add Product Image", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })

    @RequestMapping(value = "/product/{id}/uploadFile", method = RequestMethod.POST)
    public ResponseEntity addProductImage(@RequestPart(value = "file") MultipartFile file,
                                      @PathVariable("id") BigDecimal id) {
        return adminService.addProductImage(file, id);
    }


    /**
     * @param categoryId
     * @param subCategoryId
     * @param popularity
     * @return
     */
    @ApiOperation(value = "Get All Products", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST),
            @ApiResponse(code = 404, message = SasyaConstants.NOT_FOUND)
    })
    @RequestMapping(value = "/product/getProducts", method = RequestMethod.GET)
    public ResponseEntity getAllProducts(@RequestParam(value = "categoryId", required = false) BigDecimal categoryId,
                                         @RequestParam(value = "subCategoryId", required = false) BigDecimal subCategoryId,
                                         @RequestParam(value = "popularity", required = false) String popularity,
                                         @RequestParam(value = "id", required = false) List<BigDecimal> ids) {
        return commonService.getAllProducts(categoryId, subCategoryId, popularity, ids);
    }


    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete Product", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/product/{id}/deleteProduct", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id") BigDecimal id) {
        return adminService.deleteProduct(id);
    }


    /**
     * @param productId
     * @param productDto
     * @return
     */
    @ApiOperation(value = "Update Product", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/product/{product_id}/updateProduct", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    public ResponseEntity updateProduct(@PathVariable("product_id") BigDecimal productId,
                                        @Valid @RequestBody ProductDto productDto) {
        return adminService.updateProduct(productId, productDto);
    }


    /**
     * @param file
     * @param subCategoryName
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "Add Sub Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SasyaConstants.CREATED, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })

    @RequestMapping(value = "/subCategory/addSubCategory", method = RequestMethod.POST)
    public ResponseEntity addSubCategory(@RequestPart(value = "file") MultipartFile file,
                                         @RequestParam("subCategoryName") String subCategoryName,
                                         @RequestParam("categoryId") BigDecimal categoryId) {
        return adminService.addSubCategory(file, subCategoryName, categoryId);
    }


    /**
     * @param id
     * @return
     */
    @ApiOperation(value = "Remove Sub Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/subCategory/{id}/removeSubCategory", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubCategory(@PathVariable("id") BigDecimal id) {
        return adminService.deleteSubCategory(id);
    }


    /**
     * @param id
     * @param file
     * @param subCategoryName
     * @return
     */
    @ApiOperation(value = "Update Sub Category", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/subCategory/{id}/updateSubCategory", method = RequestMethod.PUT)
    public ResponseEntity updateSubCategory(@PathVariable("id") BigDecimal id, @RequestPart(value = "file")
            MultipartFile file, @RequestParam(value = "subCategoryName", required = false) String subCategoryName) {
        return adminService.updateSubCategory(id, file, subCategoryName);
    }

    /**
     * @return
     */
    @ApiOperation(value = "Get All Sub Categories", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/subCategory/getAllSubCategory", method = RequestMethod.GET)
    public ResponseEntity getAllSubCategories() {
        return commonService.getAllSubCategory();
    }


    /**
     * @return
     */
    @ApiOperation(value = "Get All Users", response = SasyaResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SasyaConstants.OK, response = SasyaResponse.class),
            @ApiResponse(code = 500, message = SasyaConstants.INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = SasyaConstants.INVALID_REQUEST)
    })
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestParam(value = "id", required = false) List<BigDecimal> ids) {
        return adminService.getAllUsers(ids);
    }

}
