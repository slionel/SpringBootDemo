package com.zz.controller;

import com.zz.entity.OrderProduct;
import com.zz.service.OrderProductService;
import com.zz.utils.Keyutils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.Kernel;


@RestController
@RequestMapping("pcontroller")
public class OrderProductController {
    @Resource
    OrderProductService orderProductService;

    @RequestMapping("add")
    /**
     * public OrderProduct addProduct(@RequestBody OrderProduct orderProduct);
     */
    public OrderProduct addProduct(HttpServletRequest request){
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productType = request.getParameter("productType");
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(Keyutils.genUniqueKey());
        orderProduct.setProductName(productName);
        orderProduct.setProductPrice(productPrice);
        orderProduct.setProductType(productType);
        return orderProductService.addProduct(orderProduct);
    }

    @RequestMapping("page")
    public Page<OrderProduct> pageTest(HttpServletRequest request){
        int startPage = Integer.parseInt(request.getParameter("page"));
        return orderProductService.findAll(startPage,5);
    }

}
