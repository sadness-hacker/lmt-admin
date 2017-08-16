package com.lmt.common.action;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lmt.common.beans.editors.CustomDateEditor;

/**
 * 
 * @author ducx
 * @date 2017-07-04
 * 基础action,添加对Date类型的绑定
 *
 */
public class BaseAction {
	
	@InitBinder 
    protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
	}
}
