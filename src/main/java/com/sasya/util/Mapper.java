package com.sasya.util;

import com.sasya.constant.SasyaConstants;
import com.sasya.dto.AddressDto;
import com.sasya.dto.ProductDto;
import com.sasya.dto.UserDto;
import com.sasya.model.Address;
import com.sasya.model.Product;
import com.sasya.model.Register;
import com.sasya.model.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Mapper {


    /**
     * @param userDto
     * @param register
     * @return
     */
    public static User convertUserDtoToUserModel(UserDto userDto, Register register) {
        User user = new User();
        user.setActive(SasyaConstants.ACTIVE);
        user.setCreatedBy(SasyaConstants.SYSTEM_USER);
        user.setCreatedDate(SasyaConstants.formatter.format(new Date()));
        user.setDeviceId(userDto.getDeviceId());
        user.setDeviceType(userDto.getDeviceType());
        user.setEmail(userDto.getEmail());
        user.setFamilyMembersCount(userDto.getFamilyMembersCount());
        user.setUserName(userDto.getUserName());
        user.setRegisterId(register.getId());
        user.setPhone(register.getPhone());
        return user;
    }


    /**
     * @param user
     * @return
     */
    public static UserDto convertUserModelToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setDeviceId(user.getDeviceId());
        userDto.setDeviceType(user.getDeviceType());
        userDto.setEmail(user.getEmail());
        userDto.setFamilyMembersCount(user.getFamilyMembersCount());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static Address convertAddressDtoToEntity(AddressDto addressDto, User user) {
        Address address = convertAddressDtoToEntity(addressDto, new Address());
        address.setCreatedDate(SasyaConstants.formatter.format(new Date()));
        address.setCreatedBy(user.getUserName());
        address.setUser(user);
        return address;
    }

    public static Address convertAddressDtoToEntity(AddressDto addressDto, Address addressEntity) {
        addressEntity.setAddress(addressDto.getAddress());
        addressEntity.setAddressType(addressDto.getAddressType());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setLandmark(addressDto.getLandmark());
        addressEntity.setPincode(new BigDecimal(addressDto.getPincode()));
        addressEntity.setSecondaryMobile(addressDto.getSecondary_mobile());
        addressEntity.setState(addressDto.getState());
        addressEntity.setActive("1");
        return addressEntity;
    }


    public static void convertDTOObjectToEntity(ProductDto productDto, Product product) {
        product.setCategoryId(Objects.nonNull(productDto.getCategoryId()) ? productDto.getCategoryId() : null);
        product.setSubCategoryId(Objects.nonNull(productDto.getSubCategoryId()) ? productDto.getSubCategoryId() : null);
        product.setProductName(Objects.nonNull(productDto.getProductName()) ? productDto.getProductName() : null);
        product.setDescription(Objects.nonNull(productDto.getDescription()) ? productDto.getDescription() : null);
        product.setWeight(Objects.nonNull(productDto.getWeight()) ? productDto.getWeight() : null);
        product.setUnit(Objects.nonNull(productDto.getUnit()) ? productDto.getUnit() : null);
        product.setPrice(Objects.nonNull(productDto.getPrice()) ? productDto.getPrice() : null);
        product.setDiscountPercent(Objects.nonNull(productDto.getDiscountPercent()) ? productDto.getDiscountPercent() : null);
        product.setGstPercent(Objects.nonNull(productDto.getGstPercent()) ? productDto.getGstPercent() : null);
        product.setActive(Objects.nonNull(productDto.getActive()) ? productDto.getActive() : null);
        product.setSubscriptionAllowed(Objects.nonNull(productDto.getSubscriptionAllowed()) ? productDto.getSubscriptionAllowed() : null);
        product.setPopularity(Objects.nonNull(productDto.getPopularity()) ? productDto.getPopularity() : null);
        product.setProductNewDate(Objects.nonNull(productDto.getProductNewDate()) ? productDto.getProductNewDate() : null);
        product.setProductUpdateDate(Objects.nonNull(productDto.getProductUpdateDate()) ? productDto.getProductUpdateDate() : null);
        product.setCreatedBy(SasyaConstants.SYSTEM_USER);
        product.setCreatedDate(SasyaConstants.formatter.format(new Date()));
    }

    public static void convertEntityToDTOObject(Product product, ProductDto productDto) {
        productDto.setProductId(product.getId());
        productDto.setCategoryId(Objects.nonNull(product.getCategoryId()) ? product.getCategoryId() : null);
        if (product.getCategory() != null) {
            productDto.setCategoryName(Objects.nonNull(product.getCategory().getName()) ? (product.getCategory().getName()) : null);
        }
        if (product.getSubCategory() != null) {
            productDto.setSubCategoryName(Objects.nonNull(product.getSubCategory().getName()) ? product.getSubCategory().getName() : null);
        }
        productDto.setSubCategoryId(Objects.nonNull(product.getSubCategoryId()) ? product.getSubCategoryId() : null);
        productDto.setProductName(Objects.nonNull(product.getProductName()) ? product.getProductName() : null);
        productDto.setDescription(Objects.nonNull(product.getDescription()) ? product.getDescription() : null);
        productDto.setWeight(Objects.nonNull(product.getWeight()) ? product.getWeight() : null);
        productDto.setUnit(Objects.nonNull(product.getUnit()) ? product.getUnit() : null);
        productDto.setPrice(Objects.nonNull(product.getPrice()) ? product.getPrice() : null);
        productDto.setImage_url(Objects.nonNull(product.getImage()) ? product.getImage() : null);
        productDto.setDiscountPercent(Objects.nonNull(product.getDiscountPercent()) ? product.getDiscountPercent() : null);
        productDto.setGstPercent(Objects.nonNull(product.getGstPercent()) ? product.getGstPercent() : null);
        productDto.setActive(Objects.nonNull(product.getActive()) ? product.getActive() : null);
        productDto.setSubscriptionAllowed(Objects.nonNull(product.getSubscriptionAllowed()) ? product.getSubscriptionAllowed() : null);
        productDto.setPopularity(Objects.nonNull(product.getPopularity()) ? product.getPopularity() : null);
        productDto.setNetprice(SasyaUtils.getNetPrice(product.getPrice(), product.getDiscountPercent(), product.getGstPercent()));
        productDto.setProductNewDate(Objects.nonNull(product.getProductNewDate()) ? product.getProductNewDate() : null);
        productDto.setProductUpdateDate(Objects.nonNull(product.getProductUpdateDate()) ? product.getProductUpdateDate() : null);
    }
}
