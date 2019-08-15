package com.zsj.service;

import com.zsj.entity.User;
import com.zsj.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zsj55
 */
@Service
public class UserService {
    @Resource
    UserRepository userRepository;

    public User register(User user){
        return userRepository.save(user);
    }

    public boolean login(String name, String pwd){
        boolean rs = false;
        User user = userRepository.findByNameAndPwd(name, pwd);
        if(user != null){
            rs = true;
        }
        return rs;
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }
}
