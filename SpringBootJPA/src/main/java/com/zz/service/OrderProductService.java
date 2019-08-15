package com.zz.service;

import com.zz.entity.OrderProduct;
import com.zz.repository.OrderProductRespository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderProductService {
    @Resource
    OrderProductRespository orderProductRespository;

    public OrderProduct addProduct(OrderProduct product){
        return orderProductRespository.save(product);
    }
}
