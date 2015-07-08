/**
 * @功能 对象的服务层
 */

package com.gxws.cms.service;

import java.io.Serializable;
import java.util.List;

import com.gxws.cms.dao.BaseDao;
import com.gxws.cms.vo.View;

abstract public class BaseService<T> {

	protected abstract BaseDao<T> getDAO();

	/**
	 * @功能 获得第一个对象
	 */
	public T getFirst() {
		List<T> list = this.getListByNum("order by o.id asc", null, 1);

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	/**
	 * @功能 获得最后一个对象
	 */
	public T getLast() {
		List<T> list = getListByNum(1);

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	/**
	 * @功能 清除一级缓存的数据
	 */
	public void clear() {
		getDAO().clear();
	}

	/**
	 * @功能 删除实体
	 */
	public void delete(Serializable id) {
		getDAO().delete(id);
	}

	/**
	 * @功能 删除实体
	 */
	public void delete(T one) {
		getDAO().delete(one);
	}

	/**
	 * @功能 获取实体
	 */
	public T get(Serializable id) {
		return getDAO().get(id);
	}

	/**
	 * @功能 保存实体
	 */
	public void save(T one) {
		getDAO().save(one);
	}

	/**
	 * @功能 更新实体
	 */
	public void update(T one) {
		getDAO().update(one);
	}

	/**
	 * @功能 批量处理
	 */
	public void executeUpdate(String hql) {
		this.executeUpdate(hql, null);
	}

	/**
	 * @功能 批量处理(带查询参数)
	 */
	public void executeUpdate(String hql, final Object[] queryParams) {
		getDAO().executeUpdate(hql, queryParams);
	}

	/**
	 * @功能 聚合查询
	 */
	public Object juhe(String hql) {
		return getDAO().juhe(hql);
	}

	/**
	 * @功能 聚合查询（带查詢）
	 */
	public Object juhe(String hql, Object[] queryParams) {
		return getDAO().juhe(hql, queryParams);
	}

	/**
	 * @功能 获取记录总数
	 */
	public Long getCount() {
		return getDAO().getCount();
	}

	/**
	 * @功能 获取记录总数（带查詢）
	 */
	public Long getCount(String where, Object[] queryParams) {
		return getDAO().getCount(where, queryParams);
	}

	/**
	 * @功能 获得所有对象
	 */
	public List<T> getList() {
		return getDAO().getList();
	}

	/**
	 * @功能 获得所有对象(带查询)
	 */
	public List<T> getList(String where, Object[] queryParams) {

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		return getDAO().getList(where, queryParams);
	}

	/**
	 * @功能 获得指定数量对象
	 */
	public List<T> getListByNum(Integer num) {
		return getDAO().getListByNum(num);
	}

	/**
	 * @功能 获得指定数量对象（带查询）
	 */
	public List<T> getListByNum(String where, Object[] queryParams, Integer num) {

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		return getDAO().getListByNum(where, queryParams, num);
	}

	/**
	 * @功能 分页
	 */
	public View<T> getView(View<T> view) {

		// 获得记录数
		long totalRecord = getDAO().getCount();
		view.setRecordTotal(totalRecord);

		// 分页结果集
		List<?> records = getDAO().getListByPage(view);
		view.setRecords(records);

		return view;
	}

	/**
	 * @功能 分页(带查詢)
	 */
	public View<?> getView(String where, View<?> view, Object[] queryParams) {

		// 获得记录数
		long totalRecord = getDAO().getCount(where, queryParams);
		view.setRecordTotal(totalRecord);

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		// 分页结果集
		List<?> records = getDAO().getListByPage(view, where, queryParams);
		view.setRecords(records);

		return view;
	}

}
