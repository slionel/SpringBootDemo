package com.zsj.controller;

import com.zsj.DTO.UserLoginDTO;
import com.zsj.entity.AngularUser;
import com.zsj.service.UserService;
import com.zsj.utils.Keyutils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsj55
 */
@RestController
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("register")
    public int register(@RequestBody AngularUser angularUser){
        angularUser.setId(Keyutils.genUniqueKey());
        return userService.register(angularUser);
    }

    @PostMapping("login")
    public Map login(@RequestBody UserLoginDTO userLoginDTO){
        Map map = new HashMap();
        map.put("rs",userService.login(userLoginDTO));
        return map;
    }
}
