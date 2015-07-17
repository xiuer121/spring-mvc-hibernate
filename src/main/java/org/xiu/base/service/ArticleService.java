/**
 * @功能 公告的服务层
 */

package org.xiu.base.service;




import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xiu.base.dao.BaseDao;
import org.xiu.base.po.Article;









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
