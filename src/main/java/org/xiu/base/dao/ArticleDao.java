package org.xiu.base.dao;




import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.xiu.base.po.Article;




@Scope("prototype")			  
@Repository      
public class ArticleDao extends BaseDao<Article>{
	

}

