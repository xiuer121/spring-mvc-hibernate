package org.xiu.base.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		
		//获取站点路径
		String ctx = sc.getContextPath();
		//设置ctx到作用域
		sc.setAttribute("ctx", ctx);
		System.out.println("ctx:"+ctx);
	}

}
