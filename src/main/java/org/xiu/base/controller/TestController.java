package org.xiu.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xiu.base.po.ColumnTb;
import org.xiu.base.service.ColumnTbService;
import org.xiu.base.vo.View;

@Controller 
@RequestMapping("/appliaction")
public class TestController {
	View<ColumnTb> view = new View<ColumnTb>();
	@Resource
	private ColumnTbService columnTbService;

	
	@RequestMapping("")
	public String getStr(HttpServletRequest req, Model m) {
		view = columnTbService.getView(view);
		System.out.println(view.getRecords().size()); 
		return "/admin/application";
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest req, Model m) {
		view = columnTbService.getView(view);
		System.out.println(view.getRecords().size());
		
		return "/admin/index";
	}
}
