package com.lmt.admin.service;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IAdminMapper;
import com.lmt.admin.model.Admin;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@Service
public class AdminService extends MybatisBaseService<IAdminMapper, Admin, Integer> implements IAdminService {

}
