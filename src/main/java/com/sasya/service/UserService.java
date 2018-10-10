package com.sasya.service;


import com.sasya.models.User;

import java.util.List;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {

    User findByUsername(String username);
    List<User> findAll();
}
