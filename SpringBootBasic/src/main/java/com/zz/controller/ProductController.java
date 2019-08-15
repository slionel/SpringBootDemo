package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.entity.Product;
import com.zz.entity.User;
import com.zz.service.WarehouseService;
import com.zz.utils.Keyutils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pcontroller")
public class ProductController {
    /**
     * 使用spring的ioc 控制反转 让spring容器帮我们创建对象
     * 需要再service上面添加 @service 注解，以及在controller给需要使用ioc创建的类添加 @Resource 注解
     * WarehouseService 对象
     */
    @Resource
    WarehouseService ws;

    @RequestMapping("all")
    public List<Product> getAll() {
        return ws.getAllProduct();
    }

    @RequestMapping("update")
    public Map update(HttpServletRequest request) {
        Map map = new HashMap();
        String id = request.getParameter("id");
        String pname = request.getParameter("pname");
        String category = request.getParameter("category");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String produceDate = request.getParameter("produceDate");
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(produceDate);
            String shelfLife = request.getParameter("shelfLife");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            Product product = new Product(id, pname, category, shelfLife, date, stock, price);
            boolean result = ws.updateAll(product);
            map.put("result", result);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("delete")
    public Map delete(HttpServletRequest request) {
        Map map = new HashMap();
        String id = request.getParameter("id");
        boolean rs = ws.deleteAllById(id);
        map.put("result", rs);
        return map;
    }

    @RequestMapping("login")
    public Map login(HttpServletRequest request) {
        Map map = new HashMap();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        WarehouseService ws = new WarehouseService();
        boolean rs = ws.login(username, password);
        map.put("result", rs);
        return map;
    }

    @RequestMapping("login2")
    public void loginMessage(HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        HttpSession session = request.getSession();
        session.setAttribute("loginName",loginName);
    }

    @RequestMapping("login3")
    public Map login3(HttpServletRequest request) {
        Map map = new HashMap();
        HttpSession session = request.getSession();
        String loginName = (String) session.getAttribute("loginName");
        map.put("loginname",loginName);
        return map;
    }

    @RequestMapping("register")
    public Map register(HttpServletRequest request) {
        Map map = new HashMap();
        String utype = request.getParameter("utype");
        String userName = request.getParameter("user_name");
        String pwd = request.getParameter("pwd");
        String repwd = request.getParameter("repwd");
        User user = new User(userName, pwd, utype);
        user.setUid(Keyutils.genUniqueKey());
        WarehouseService ws = new WarehouseService();
        boolean rs = ws.register(user);
        map.put("result", rs);
        return map;
    }

    @RequestMapping("getbyname")
    public void findByName(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String pname = request.getParameter("pname");
        String json = JSONObject.toJSONString(ws.findByPname(pname));
        try {
            response.getWriter().append(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("add")
    public Map addProduct(HttpServletRequest request) {
        Map map = new HashMap();
        String pid = Keyutils.genUniqueKey();
        String pname = request.getParameter("pname");
        String pnum = request.getParameter("pnum");
        String category = request.getParameter("category");
        String shelfLife = request.getParameter("shelflife");
        String date = request.getParameter("producedate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date produceDate = simpleDateFormat.parse(date);
            int stock = Integer.parseInt(request.getParameter("stock"));
            String price = request.getParameter("price");
            Product product = new Product(pid,pname,category,shelfLife,produceDate,stock,new BigDecimal(price));
            map.put("result",ws.productAdd(product));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }
}
