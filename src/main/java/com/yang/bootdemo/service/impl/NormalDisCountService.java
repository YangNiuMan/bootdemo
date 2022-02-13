package com.yang.bootdemo.service.impl;

import com.yang.bootdemo.service.DiscountStrategy;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@Service
public class NormalDisCountService implements DiscountStrategy {
    @Override
    public String getType() {
        return "normal";
    }

    @Override
    public double disCount(double fee) {
        return fee * 1.0;
    }
}
