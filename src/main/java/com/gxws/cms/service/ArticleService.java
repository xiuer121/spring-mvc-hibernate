/**
 * @功能 公告的服务层
 */

package com.gxws.cms.service;




import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gxws.cms.dao.BaseDao;
import com.gxws.cms.po.Article;









@Scope("prototype")						
@Service				
public class ArticleService extends BaseService<Article>{

	@Resource(name="articleDao")
	private  BaseDao<Article> articleDao;
	
	@Override
	protected BaseDao<Article> getDAO() {
		return articleDao;
	}



}
