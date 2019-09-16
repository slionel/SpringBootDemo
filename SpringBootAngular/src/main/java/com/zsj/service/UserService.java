package com.zsj.service;

import com.zsj.DTO.UserLoginDTO;
import com.zsj.entity.AngularUser;
import com.zsj.mapper.AngularUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zsj55
 */
@Service
public class UserService {
    @Resource
    AngularUserMapper angularUserMapper;

    public int register(AngularUser angularUser){
        return angularUserMapper.insert(angularUser);
    }

    public AngularUser login(UserLoginDTO userLoginDTO){
        return angularUserMapper.login(userLoginDTO);
    }
}
