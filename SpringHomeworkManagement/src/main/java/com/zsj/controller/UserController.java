package com.zsj.controller;

import com.zsj.service.UserService;
import com.zsj.entity.User;
import com.zsj.utils.Keyutils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsj55
 */
@RestController
@RequestMapping("ucontroller")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("register")
    public Map register(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        boolean rs = false;
        Map map = new HashMap();
        String status = request.getParameter("status");
        String name = request.getParameter("user_name");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User();
        user.setId(Keyutils.genUniqueKey());
        user.setName(name);
        user.setPwd(pwd);
        user.setStatus(status);
        user.setAge(age);
        user.setSex(sex);
        if (userService.register(user).getId() != null && userService.register(user).getName() != null){
            rs = true;
            map.put("result",rs);
        }
        else{
            map.put("result",rs);
        }
        return map;
    }

    @RequestMapping("login")
    public Map login(HttpServletRequest request){
        Map map = new HashMap();
        boolean rs;
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        rs = userService.login(name,pwd);
        map.put("result",rs);
        return map;
    }

    @RequestMapping("login2")
    public void getLoginMesg(HttpServletRequest request){
        String loginName = request.getParameter("loginName");
        HttpSession session = request.getSession();
        session.setAttribute("name",loginName);
        User user = userService.findByName(loginName);
        session.setAttribute("status",user.getStatus());
        session.setAttribute("uid",user.getId());
    }

    @RequestMapping("login3")
    public Map getLoginName(HttpServletRequest request){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        String loginname = (String) session.getAttribute("name");
        String status = (String) session.getAttribute("status");
        String uid = (String) session.getAttribute("uid");
        map.put("loginname",loginname);
        map.put("status",status);
        map.put("uid",uid);
        return map;
    }








    /*@RequestMapping("register")
    public Map register(@RequestBody  User user){
        boolean rs = false;
        Map map = new HashMap();
        user.setId(Keyutils.genUniqueKey());

        if(userService.register(user).getId() != null){
            rs = true;
            map.put("rs",rs);
        }
        else{
            map.put("rs",rs);
        }
        return map;
    }*/
}
