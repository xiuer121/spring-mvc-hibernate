/**
 * @功能 公告的服务层
 */

package com.gxws.cms.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gxws.cms.dao.BaseDao;
import com.gxws.cms.po.ColumnTb;

@Scope("prototype")
@Service
public class ColumnTbService extends BaseService<ColumnTb> {

	@Resource(name = "columnTbDao")
	private BaseDao<ColumnTb> columnTbDao;

	@Override
	protected BaseDao<ColumnTb> getDAO() {
		return columnTbDao;
	}

}
