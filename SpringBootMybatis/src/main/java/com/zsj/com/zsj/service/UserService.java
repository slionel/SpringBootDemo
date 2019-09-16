package com.zsj.com.zsj.service;

import com.zsj.entity.UserInfo;
import com.zsj.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserInfoMapper userInfoMapper;

    public List<UserInfo> selectAll(){
        return userInfoMapper.selectAll();
    }

    public UserInfo select(String username){
        return userInfoMapper.select(username);
    }
}
