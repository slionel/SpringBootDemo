package com.zsj.controller;

import com.zsj.service.UserService;
import com.zsj.entity.User;
import com.zsj.utils.Keyutils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
        session.setAttribute("sex",user.getSex());
    }

    @RequestMapping("login3")
    public Map getLoginName(HttpServletRequest request){
        Map map = new HashMap();
        HttpSession session = request.getSession();
        String loginname = (String) session.getAttribute("name");
        String status = (String) session.getAttribute("status");
        String uid = (String) session.getAttribute("uid");
        String sex = (String) session.getAttribute("sex");
        map.put("loginname",loginname);
        map.put("status",status);
        map.put("uid",uid);
        map.put("sex",sex);
        return map;
    }

    @RequestMapping("getbyid")
    public User getById(HttpServletRequest request){
        List<User> userList = new ArrayList<User>();
        String uid = request.getParameter("uid");
        Optional<User> optionalUser = userService.findById(uid);
        User user = optionalUser.get();
        return  user;
    }

    @RequestMapping("updatedata")
    public Map addFileName(HttpServletRequest request){
        Map map = new HashMap();
        boolean rs = false;
        String uid = request.getParameter("uid");
        String fileName = request.getParameter("fileName");
        String userName = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String status = request.getParameter("status");
        User user = new User();
        user.setId(uid);
        user.setImgId(fileName);
        user.setAge(age);
        user.setPwd(pwd);
        user.setName(userName);
        user.setSex(sex);
        user.setStatus(status);
        if(userService.saveImgName(user).getId() != null){
            rs = true;
            map.put("result",rs);
        }
        else{
            map.put("result",rs);
        }

        return map;
    }

    @RequestMapping("imgupload")
    public Map imagUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        boolean rs = false;
        if(!file.isEmpty()){
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            String savePath = "C:\\Users\\zsj55\\Desktop\\头像文件";
            map.put("fileName",fileName);
            File dest = new File(savePath+File.separator+fileName);
            try {
                file.transferTo(dest);
                rs = true;
                map.put("result",rs);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("result",rs);
            }
        }
        else if (file.isEmpty()){
            map.put("result",rs);
        }
        return map;
    }
}
