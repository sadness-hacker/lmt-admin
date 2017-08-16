package com.lmt.admin.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.mapper.IBaseMapper;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@MapperScan
public interface IResourceMapper extends IBaseMapper<Resource, Integer> {

}
