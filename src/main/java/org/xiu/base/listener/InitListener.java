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
		
		//��ȡվ��·��
		String ctx = sc.getContextPath();
		//����ctx��������
		sc.setAttribute("ctx", ctx);
		System.out.println("ctx:"+ctx);
	}

}
