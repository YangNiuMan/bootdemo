package com.yang.bootdemo.service;

public interface DiscountStrategy {
    
    String  getType();
    
    double disCount(double fee);
}
