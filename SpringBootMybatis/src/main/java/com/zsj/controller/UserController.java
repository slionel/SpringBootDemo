package com.zsj.controller;

import com.zsj.com.zsj.service.UserService;
import com.zsj.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ResponseBody
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("selectAll")
    public List<UserInfo> selectAll(){
        return userService.selectAll();
    }

    @GetMapping("select/{username}")
    public UserInfo select(@PathVariable("username") String username){
        return userService.select(username);
    }
}
