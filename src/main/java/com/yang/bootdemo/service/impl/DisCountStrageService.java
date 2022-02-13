package com.yang.bootdemo.service.impl;

import com.yang.bootdemo.service.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yang
 */
@Service
public class DisCountStrageService {

    Map<String, DiscountStrategy> map = new HashMap<>();


    public DisCountStrageService(List<DiscountStrategy> strategies) {
        strategies.forEach(s -> map.put(s.getType(), s));
    }


    public double discount(String type, double fee){
        DiscountStrategy discountStrategy = map.get(type);
        return discountStrategy.disCount(fee);
    }
}
