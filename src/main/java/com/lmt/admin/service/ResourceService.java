package com.lmt.admin.service;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IResourceMapper;
import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-17
 *
 */
@Service
public class ResourceService extends MybatisBaseService<IResourceMapper, Resource, Integer> implements IResourceService {

}
