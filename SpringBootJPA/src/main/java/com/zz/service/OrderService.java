package com.zz.service;

import com.zz.entity.OrderMaster;
import com.zz.OrderMasterRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class OrderService {

    @Resource
    OrderMasterRepository masterRepository;
    public OrderMaster addMaster(OrderMaster orderMaster){
        return masterRepository.save(orderMaster);
    }

    public ArrayList<OrderMaster> showMaster(OrderMaster orderMaster){
        return (ArrayList<OrderMaster>) masterRepository.findAll();
    }
}
