package com.zsj.mapper;

import com.zsj.DTO.UserLoginDTO;
import com.zsj.entity.AngularUser;

/**
 * @author zsj55
 */
public interface AngularUserMapper {
    int insert(AngularUser record);

    int insertSelective(AngularUser record);

    AngularUser login(UserLoginDTO userLoginDTO);
}