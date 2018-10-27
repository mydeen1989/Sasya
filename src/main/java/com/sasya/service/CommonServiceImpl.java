package com.sasya.service;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.CategoryDto;
import com.sasya.exception.SasyaException;
import com.sasya.model.Category;
import com.sasya.repository.CommonDAO;
import com.sasya.response.SasyaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * UserServiceImpl
 */
@Service
public class CommonServiceImpl {


    @Inject
    private CommonDAO commonDAO;


    public ResponseEntity getAllCategory() {
        try {
            List<Category> categories = commonDAO.getAllCategory();
            if(CollectionUtils.isEmpty(categories)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(SasyaResponse.build(SasyaConstants.FAILURE,SasyaConstants.CATEGORY_NOT_FOUND));
            }
            List<CategoryDto> categoryDtos = new ArrayList<>();
            categories.forEach(category -> {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                categoryDto.setImage_url(category.getImage());
                categoryDto.setActive(category.getActive());
                categoryDtos.add(categoryDto);
            });
            return ResponseEntity.ok().body(categoryDtos);
        } catch (SasyaException sasyaExp){
            throw sasyaExp;
        }
        catch (Exception exp) {
            throw new SasyaException(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
