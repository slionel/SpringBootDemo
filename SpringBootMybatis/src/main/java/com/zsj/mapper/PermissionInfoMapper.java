package com.zsj.mapper;

import com.zsj.entity.PermissionInfo;

public interface PermissionInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);
}