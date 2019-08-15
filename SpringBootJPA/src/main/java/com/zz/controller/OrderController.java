package com.zz.controller;

import com.zz.entity.OrderMaster;
import com.zz.service.OrderService;
import com.zz.utils.Keyutils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @RestController表示返回类型都是json格式
 */
@RestController
@RequestMapping("ordermaster")
public class OrderController {
    @Resource
    OrderService orderService;

    @RequestMapping("add")
    public OrderMaster save(HttpServletRequest request){
        String address = request.getParameter("address");
        String name = request.getParameter("name");
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId(Keyutils.genUniqueKey());
        orderMaster.setAddress(address);
        orderMaster.setName(name);
        return orderService.addMaster(orderMaster);
    }


    @RequestMapping("showall")
    public ArrayList<OrderMaster> showAll(HttpServletRequest request){
        OrderMaster orderMaster = new OrderMaster();
        return orderService.showMaster(orderMaster);
    }
}
