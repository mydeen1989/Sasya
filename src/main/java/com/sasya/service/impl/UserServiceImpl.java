package com.sasya.service.impl;

import com.sasya.service.UserService;
import com.sasya.models.User;
import com.sasya.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fan.jin on 2016-10-15.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userRepository;

    @Override
    public User findByUsername(String username ) throws UsernameNotFoundException {
        User u = userRepository.findByUsername( username );
        return u;
    }


    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }
}
