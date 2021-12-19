package com.yang.bootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.bootdemo.entity.TbTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang
 */
@Mapper
public interface TbTestMapper extends BaseMapper<TbTest> {
}
