package com.sasya.repository;

import com.sasya.models.UserRegister;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterDAO extends CrudRepository<UserRegister, Integer> {

}
