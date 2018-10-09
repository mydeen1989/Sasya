package com.sasya.business;

import com.sasya.exception.SasyaException;
import com.sasya.models.User;
import com.sasya.models.UserRegister;
import com.sasya.repository.UserDAO;
import com.sasya.repository.UserRegisterDAO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Service(value="userService")
public class UserRegisterImpl implements UserDetailsService {

    @Inject
    private UserRegisterDAO registerDAO;

    @Inject
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }


    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public User findOne(String username) {
        return userDAO.findByUsername(username);
    }

    public User findById(Long id) {
        return userDAO.findById(id).get();
    }

    public void registerUser(String phone) {
        try {
            UserRegister register = new UserRegister();
            register.setPhone(phone);
            register.setActivationCode("0000");
            register.setCreatedBy("user1");
            registerDAO.save(register);
        } catch (Exception exp) {
            throw new SasyaException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
