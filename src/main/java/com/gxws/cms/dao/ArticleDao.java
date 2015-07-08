package com.gxws.cms.dao;




import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.gxws.cms.po.Article;




@Scope("prototype")			  
@Repository      
public class ArticleDao extends BaseDao<Article>{
	

}

