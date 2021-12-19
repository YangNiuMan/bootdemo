package com.yang.bootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.bootdemo.entity.TbTest;
import com.yang.bootdemo.mapper.TbTestMapper;
import com.yang.bootdemo.service.TbTestService;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@Service
public class TbTestServiceImpl extends ServiceImpl<TbTestMapper, TbTest> implements TbTestService {
}
