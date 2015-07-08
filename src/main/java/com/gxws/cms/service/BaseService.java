/**
 * @���� ����ķ����
 */

package com.gxws.cms.service;

import java.io.Serializable;
import java.util.List;

import com.gxws.cms.dao.BaseDao;
import com.gxws.cms.vo.View;

abstract public class BaseService<T> {

	protected abstract BaseDao<T> getDAO();

	/**
	 * @���� ��õ�һ������
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
	 * @���� ������һ������
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
	 * @���� ���һ�����������
	 */
	public void clear() {
		getDAO().clear();
	}

	/**
	 * @���� ɾ��ʵ��
	 */
	public void delete(Serializable id) {
		getDAO().delete(id);
	}

	/**
	 * @���� ɾ��ʵ��
	 */
	public void delete(T one) {
		getDAO().delete(one);
	}

	/**
	 * @���� ��ȡʵ��
	 */
	public T get(Serializable id) {
		return getDAO().get(id);
	}

	/**
	 * @���� ����ʵ��
	 */
	public void save(T one) {
		getDAO().save(one);
	}

	/**
	 * @���� ����ʵ��
	 */
	public void update(T one) {
		getDAO().update(one);
	}

	/**
	 * @���� ��������
	 */
	public void executeUpdate(String hql) {
		this.executeUpdate(hql, null);
	}

	/**
	 * @���� ��������(����ѯ����)
	 */
	public void executeUpdate(String hql, final Object[] queryParams) {
		getDAO().executeUpdate(hql, queryParams);
	}

	/**
	 * @���� �ۺϲ�ѯ
	 */
	public Object juhe(String hql) {
		return getDAO().juhe(hql);
	}

	/**
	 * @���� �ۺϲ�ѯ������ԃ��
	 */
	public Object juhe(String hql, Object[] queryParams) {
		return getDAO().juhe(hql, queryParams);
	}

	/**
	 * @���� ��ȡ��¼����
	 */
	public Long getCount() {
		return getDAO().getCount();
	}

	/**
	 * @���� ��ȡ��¼����������ԃ��
	 */
	public Long getCount(String where, Object[] queryParams) {
		return getDAO().getCount(where, queryParams);
	}

	/**
	 * @���� ������ж���
	 */
	public List<T> getList() {
		return getDAO().getList();
	}

	/**
	 * @���� ������ж���(����ѯ)
	 */
	public List<T> getList(String where, Object[] queryParams) {

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		return getDAO().getList(where, queryParams);
	}

	/**
	 * @���� ���ָ����������
	 */
	public List<T> getListByNum(Integer num) {
		return getDAO().getListByNum(num);
	}

	/**
	 * @���� ���ָ���������󣨴���ѯ��
	 */
	public List<T> getListByNum(String where, Object[] queryParams, Integer num) {

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		return getDAO().getListByNum(where, queryParams, num);
	}

	/**
	 * @���� ��ҳ
	 */
	public View<T> getView(View<T> view) {

		// ��ü�¼��
		long totalRecord = getDAO().getCount();
		view.setRecordTotal(totalRecord);

		// ��ҳ�����
		List<?> records = getDAO().getListByPage(view);
		view.setRecords(records);

		return view;
	}

	/**
	 * @���� ��ҳ(����ԃ)
	 */
	public View<?> getView(String where, View<?> view, Object[] queryParams) {

		// ��ü�¼��
		long totalRecord = getDAO().getCount(where, queryParams);
		view.setRecordTotal(totalRecord);

		if (where.indexOf("order") == -1) {
			where = where + " order by o.id desc";
		}

		// ��ҳ�����
		List<?> records = getDAO().getListByPage(view, where, queryParams);
		view.setRecords(records);

		return view;
	}

}
