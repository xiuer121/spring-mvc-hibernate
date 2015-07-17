package org.xiu.base.dao;




import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.xiu.base.vo.View;







@SuppressWarnings("unchecked")
abstract public class BaseDao<T> extends HibernateDaoSupport{
	
	private Class<T> entityClass;
	
	public BaseDao() {
		entityClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 *@����  ���һ�����������
	 */
	public void clear(){
		this.getHibernateTemplate().clear();
	}

	/**
	 *@����  ɾ��ʵ��
	 */
	public void delete(Serializable id){
		T one=(T)this.getHibernateTemplate().get(entityClass, id);
		this.delete(one);
	}
	
	/**
	 *@����  ɾ��ʵ��
	 */
	public void delete(T one) {
		if(one!=null){
			this.getHibernateTemplate().delete(one);
		}
	}
	
	/**
	 *@����  ��ȡʵ��
	 */
	public T get(Serializable id){
		return (T)this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 *@����  ����ʵ��
	 */
	public void save(T one) {
		this.getHibernateTemplate().save(one);
	}
	
	/**
	 *@����  ����ʵ��
	 */
	public void update(T one) {
		this.getHibernateTemplate().update(one);
	}
	
	/**
	 *@���� ��������
	 */
	public void executeUpdate(final String hql) {
		this.executeUpdate(hql, null);
	}
	
	/**
	 *@���� ��������(����ѯ����)
	 */
	public void executeUpdate(final String hql,final Object[] queryParams) {
		this.getHibernateTemplate().execute(
				new HibernateCallback() { 
				
					public Object doInHibernate(Session session) throws HibernateException { 
						Query query=session.createQuery(hql);
						setQueryParams(query, queryParams);
						return query.executeUpdate();
					} 
				}
		);
	}
	
	/**
	 * @���� �ۺϲ�ѯ
	 */
	public Object juhe(String hql) {
		return getHibernateTemplate().find(hql).get(0);
	}
	
	/**
	 * @���� �ۺϲ�ѯ������ԃ��
	 */
	public Object juhe(String hql,Object[] queryParams) {
		return getHibernateTemplate().find(hql,queryParams).get(0);
	}
	
	/**
	 * @���� ��ȡ��¼����
	 */
	public Long getCount() {
		String hql="select count(o) from "+this.getEntityName()+" o ";
		return (Long)getHibernateTemplate().find(hql).get(0);
	}

	/**
	 * @���� ��ȡ��¼����������ԃ��
	 */
	public Long getCount(String where,Object[] queryParams){
		
		String hql="select count(o) from "+this.getEntityName()+" o "+where;
		return (Long)getHibernateTemplate().find(hql,queryParams).get(0);
		
	}

	/**
	 * @���� ������ж���
	 */
	public List<T> getList(){
		String hql="from "+this.getEntityName()+" o order by o.id desc";
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	/**
	 * @���� ������ж���(����ѯ)
	 */
	public List<T> getList(String where,Object[] queryParams){	
		String hql="from "+this.getEntityName()+" o "+where;
		return (List<T>) this.getHibernateTemplate().find(hql,queryParams);
	}
	
	/**
	 * @���� ���ָ����������
	 */
	public List<T> getListByNum(final Integer num){	
		
		final String hql="from "+this.getEntityName()+" o order by o.id desc";
		List<T> list = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() { 
					public Object doInHibernate(Session session) throws HibernateException { 
						Query query=session.createQuery(hql);

						return query.setMaxResults(num).list(); 
					} 
				}
			);  
			return list;
	}
	
	/**
	 * @���� ���ָ������ ����(����ѯ)
	 */
	public List<T> getListByNum(final String where,final Object[] queryParams,final Integer num){	
		
		final String hql="from "+this.getEntityName()+" o "+where;
		List<T> list = (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() { 
					public Object doInHibernate(Session session) throws HibernateException { 
						Query query=session.createQuery(hql);
						setQueryParams(query, queryParams);
						return query.setMaxResults(num).list(); 
					} 
				}
			);  
			return list;
	}
	
	/**
	 * @���� ��÷�ҳ�����
	 */
	public List<?> getListByPage(final View<?> view){
		
		final String hql="from "+this.getEntityName()+" o order by o.id desc";
		List<?> list = this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException { 
					return session.createQuery(hql)
						   		  .setFirstResult(view.getFirstResult())
						          .setMaxResults(view.getRecorderPage())
						          .list(); 
				} 
		});
 
		return list;
	}
	
	/**
	 * @���� ��÷�ҳ�����������ѯ��
	 */
	public List<?> getListByPage(final View<?> view,final String where,final Object[] queryParams){
		
		final String hql="from "+this.getEntityName()+" o "+where;

		List<?> list = getHibernateTemplate().execute(
				new HibernateCallback() { 
					public Object doInHibernate(Session session) throws HibernateException{ 
						
						Query query=session.createQuery(hql);
						setQueryParams(query, queryParams);

						return query.setFirstResult(view.getFirstResult())
				          			.setMaxResults(view.getRecorderPage())
				          			.list(); 
					} 
				}
			);  
			return list;
	}

	
	
	
	

    @Resource(name="sessionFactory")    
     public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    
	/**
	 *@����  ���ʵ������
	 */
	private String getEntityName(){
		return entityClass.getSimpleName();
	}
	
	/**
	 *@����  ƴװ��ѯ����
	 */
	private void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i, queryParams[i]);
			}
		}
	}
	
	
	
	/**
	 * ��ѯsql��䣬�����ؼ���
	 * 
	 * @author ¬��־ 2014��9��17������9:01:13
	 * @param sql
	 *            Ԥ�����sql���
	 * @param cls
	 *            �Խ�������з�װ����
	 * @param page
	 *            ��ǰҳ
	 * @param rows
	 *            ÿҳ��ʾ��¼��
	 * @param param
	 *            ��������
	 * @return List
	 */
	public <K> List<K> findSql(String sql, Class<K> cls, int page, int rows,
			List<Object> param) {

		SQLQuery q =this.currentSession().createSQLQuery(sql);

		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}

		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Type fieldType =  field.getGenericType();
			String fieldName = field.getName();
			org.hibernate.type.Type ht = null;
			if (fieldType.equals(java.lang.String.class)) {
				ht = new org.hibernate.type.StringType();
			} else if (fieldType.equals(java.lang.Integer.class)) {
				ht = new org.hibernate.type.IntegerType();
			} else if (fieldType.equals(java.math.BigDecimal.class)) {
				ht = new org.hibernate.type.BigDecimalType();
			} else if (fieldType.equals(java.lang.Float.class)) {
				ht = new org.hibernate.type.FloatType();
			} else if (fieldType.equals(java.lang.Double.class)) {
				ht = new org.hibernate.type.DoubleType();
			} else if (fieldType.equals(java.util.Date.class)) {
				ht = new org.hibernate.type.DateType();
			} else if (fieldType.equals(java.sql.Date.class)) {
				ht = new org.hibernate.type.DateType();
			} else {

			}

			q.addScalar(fieldName, ht);
		}
		q.setResultTransformer(Transformers.aliasToBean(cls));

		if (rows == -1) {
			// two -1 is no page !
		} else {
			if (page < 1) {
				page = 1;
			}
			if (rows < 1) {
				rows = 1;
			}
			q.setFirstResult((page - 1) * rows).setMaxResults(rows);
		}

		List<K> resultList = q.list();
		return resultList;

	}


	
}
