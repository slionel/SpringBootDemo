package com.zz.service;

import com.zz.entity.OrderProduct;
import com.zz.repository.OrderProductRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderProductService {
    @Resource
    OrderProductRespository orderProductRespository;

    public OrderProduct addProduct(OrderProduct product){
        return orderProductRespository.save(product);
    }

    public Page<OrderProduct> findAll(int page, int limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<OrderProduct> pageinfo = orderProductRespository.findAll(pageable);
        return pageinfo;
    }
}
