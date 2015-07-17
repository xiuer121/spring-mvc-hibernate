/**
 * @功能 公告的服务层
 */

package org.xiu.base.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xiu.base.dao.BaseDao;
import org.xiu.base.po.ColumnTb;

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
