package com.gxws.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gxws.cms.po.ColumnTb;
import com.gxws.cms.service.ColumnTbService;
import com.gxws.cms.vo.View;

@Controller
public class TestController {
	View<ColumnTb> view = new View<ColumnTb>();
	@Resource
	private ColumnTbService columnTbService;

	
	@RequestMapping("/index")
	public void getStr(HttpServletRequest req, Model m) {
		view = columnTbService.getView(view);
		System.out.println(view.getRecords().size());
	}
}
