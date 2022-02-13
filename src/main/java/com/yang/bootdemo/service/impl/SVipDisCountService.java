package com.yang.bootdemo.service.impl;

import com.yang.bootdemo.service.DiscountStrategy;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@Service
public class SVipDisCountService implements DiscountStrategy {
 
 
    @Override
    public String getType(){
        return "svip";
    }
 
    @Override
    public double disCount(double fee){
        return fee * 0.5;
    }
 
}