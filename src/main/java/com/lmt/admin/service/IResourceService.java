package com.lmt.admin.service;

import com.lmt.admin.mapper.IResourceMapper;
import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.service.IMybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-14
 *
 */
public interface IResourceService extends IMybatisBaseService<IResourceMapper, Resource, Integer> {

}
