package org.xiu.base.dao;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.totoro.util.ConditionQuery;
import com.totoro.util.OrderBy;
import com.totoro.util.Page;



public class BaseHibernateDao<T, PK extends java.io.Serializable> {



	// ��־�����

	protected static final Logger LOGGER = LoggerFactory



	.getLogger(BaseHibernateDao.class);


	// ���ͷ�����

	private Class<T> entityClass;


	// ͨ�������ȡ����ȷ���ķ�����

	@SuppressWarnings({"rawtypes","unchecked"})

	public BaseHibernateDao() {


	Type genType = getClass().getGenericSuperclass();


	Type[] params = ((ParameterizedType) genType).getActualTypeArguments();


	entityClass = (Class) params[0];

	}


	/*

	 * ע��sessionFactory

	*/

	@Autowired

	@Qualifier("sessionFactory")

	private SessionFactory sessionFactory;


	public Session getSession() {


	// ��������ǿ�����(Required)�������ȡ����


	return sessionFactory.getCurrentSession();

	}


	/*

	 * ����PO

	*/

	@SuppressWarnings("unchecked")

	public PK save(T entity) {


	return (PK) getSession().save(entity);

	}


	/*

	 * ��������PO

	*/

	public void saveOrUpdate(T entity) {


	getSession().saveOrUpdate(entity);

	}


	/*

	 * ����PO

	*/

	public void update(T entity) {


	getSession().update(entity);


	}


	/*

	 * �ϲ�PO

	*/

	public void merge(T entity) {


	getSession().merge(entity);

	}


	/*

	 * ����idɾ��PO

	*/

	public void delete(PK id) {


	getSession().delete(this.get(id));


	}


	/*

	 * ɾ��PO

	*/

	public void deleteObject(T entity) {


	getSession().delete(entity);

	}


	/*

	 * ����id�ж�PO�Ƿ����

	*/

	public boolean exists(PK id) {


	return get(id) != null;

	}


	/*

	 * ����id����PO

	*/

	@SuppressWarnings("unchecked")

	public T load(PK id) {


	return (T) getSession().load(this.entityClass, id);

	}


	/*

	 * ����id��ȡPO

	*/

	@SuppressWarnings("unchecked")

	public T get(PK id) {


	return (T) getSession().get(this.entityClass, id);

	}


	/*

	 * ��ȡPO����(Ĭ��ΪentityClass)

	*/

	public int countAll() {


	Criteria criteria = createCriteria();


	return Integer.valueOf(criteria.setProjection(Projections.rowCount())




	.uniqueResult().toString());

	}


	/*

	 * ����Criteria��ѯ��������ȡPO����

	*/

	public int countAll(Criteria criteria) {


	return Integer.valueOf(criteria.setProjection(Projections.rowCount())




	.uniqueResult().toString());

	}


	/*

	 * ɾ������

	*/

	public void deleteAll(Collection<?> entities) {


	if (entities == null)



	return;


	for (Object entity : entities) {



	getSession().delete(entity);


	}

	}


	/*

	 * ��ȡȫ������

	*/

	@SuppressWarnings("unchecked")

	public List<T> list() {


	return createCriteria().list();

	}


	/*

	 * ��ȡ�����б����Criteria

	*/

	@SuppressWarnings("unchecked")

	public List<T> list(Criteria criteria) {


	return criteria.list();

	}


	/*

	 * ���߲�ѯ

	*/

	@SuppressWarnings({"unchecked","hiding"})

	public <T> List<T> list(DetachedCriteria criteria) {


	return (List<T>) list(criteria.getExecutableCriteria(getSession()));

	}


	/*

	 * ��ȡȫ������֧������

	*

	 * @param orderBy

	*

	 * @param isAsc

	*

	 * @return

	*/

	@SuppressWarnings("unchecked")

	public List<T> list(String orderBy, boolean isAsc) {


	Criteria criteria = createCriteria();


	if (isAsc) {



	criteria.addOrder(Order.asc(orderBy));


	} else {



	criteria.addOrder(Order.desc(orderBy));


	}


	return criteria.list();

	}


	/*

	 * �����Բ��Ҷ����б�ƥ�䷽ʽΪ���

	*

	 * @param propertyName

	*

	 * @param value

	*

	 * @return

	*/

	public List<T> list(String propertyName, Object value) {


	Criterion criterion = Restrictions




	.like(propertyName,"%"+ value +"%");


	return list(criterion);

	}


	/*

	 * ���ݲ�ѯ������ȡ�����б�

	*/

	@SuppressWarnings("unchecked")

	private List<T> list(Criterion criterion) {


	Criteria criteria = createCriteria();


	criteria.add(criterion);


	return criteria.list();

	}


	/*

	 * ��Criteria��ѯ�����б�

	*

	 * @param criterions�����ɱ��Criterion

	*

	 * @param criterions

	*

	 * @return

	*/

	@SuppressWarnings("unchecked")

	public List<T> list(Criterion... criterions) {


	return createCriteria(criterions).list();

	}


	/*

	 * �����Բ���Ψһ����ƥ�䷽ʽΪ���

	*

	 * @param propertyName

	*

	 * @param value

	*

	 * @return

	*/

	@SuppressWarnings("unchecked")

	public T uniqueResult(String propertyName, Object value) {


	Criterion criterion = Restrictions.eq(propertyName, value);


	return (T) createCriteria(criterion).uniqueResult();

	}


	/*

	 * ��Criteria��ѯΨһ����

	*

	 * @param criterions�����ɱ��Criterion

	*

	 * @param criterions

	*

	 * @return

	*/

	public T uniqueResult(Criterion... criterions) {


	Criteria criteria = createCriteria(criterions);


	return uniqueResult(criteria);

	}


	/*

	 * ��Criteria��ѯΨһ����

	*

	 * @param criterions

	*

	 * @return

	*/

	@SuppressWarnings("unchecked")

	public T uniqueResult(Criteria criteria) {


	return (T) criteria.uniqueResult();

	}


	/*

	 * ΪCriteria���distinct transformer

	*

	 * @param criteria

	*

	 * @return

	*/

	// ��Ϊû��

	public Criteria distinct(Criteria criteria) {


	// �����������һ�η�װ����װ��DISTINCT_ROOT_ENTITY���󣬷���service�����ʹ��


	criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);


	return criteria;

	}


	/*

	 * ǿ�����session

	*/

	public void flush() {


	getSession().flush();

	}


	/*

	 * ���session

	*/

	public void clear() {


	getSession().clear();

	}


	/*

	 * ����Criteriaʵ��

	*/

	public Criteria createCriteria() {


	return getSession().createCriteria(entityClass);

	}


	/*

	 * ����Criterion��������Criteria

	*

	 * @param criterions�����ɱ��Criterion

	*/

	public Criteria createCriteria(Criterion... criterions) {


	Criteria criteria = createCriteria();


	for (Criterion c : criterions) {



	criteria.add(c);


	}


	return criteria;

	}


	/*

	 * ��ҳ��ѯCriteria

	*

	 * @param

	*

	 * @return

	*/

	public List<T> findPage(Criteria criteria, int pageNo, int pageSize) {


	// ������ʼ�����


	criteria.setFirstResult((pageNo - 1) * pageSize);


	// ���ص��������


	criteria.setMaxResults(pageSize);


	return list(criteria);

	}


	/*

	 * ��ҳ��ѯCriteria

	*

	 * @param

	*

	 * @return

	*/

	public Page<T> pagedQuery(Criteria criteria, int pageNo, int pageSize) {


	Assert.isTrue(pageNo >= 1,"pageNO should start from 1");



	// ���ز�ѯ�����


	List<T> list = findPage(criteria, pageNo, pageSize);


	/*


	 * ע����ΪfinaPage�����ı��˲�ѯ��������countALL������ѯΪ�գ� ���Ա�����������setFirstResultΪ0


	*/


	criteria.setFirstResult(0);


	// count��ѯ


	// ��ò�ѯ����


	long totalCount = countAll(criteria);



	if (totalCount < 1) {



	return new Page<T>();


	}



	// ʵ�ʲ�ѯ���ط�ҳ����


	int startIndex = Page.getStartOfPage(pageNo, pageSize);



	return new Page<T>(startIndex, totalCount, pageSize, list);

	}


	/*

	 * ��ҳ��ѯCriteria

	*

	 * @param

	*

	 * @return

	*/

	public Page<T> pagedQuery(ConditionQuery conditionQuery, OrderBy orderBy,



	int pageNo, int pageSize) {


	Assert.isTrue(pageNo >= 1,"pageNO should start from 1");


	Criteria criteria = createCriteria();


	// �����ѯ����������


	conditionQuery.build(criteria);


	orderBy.build(criteria);


	// count��ѯ


	// ��ò�ѯ����


	long totalCount = countAll(criteria);



	if (totalCount < 1) {



	return new Page<T>();


	}



	// ʵ�ʲ�ѯ���ط�ҳ����


	int startIndex = Page.getStartOfPage(pageNo, pageSize);


	// ���ز�ѯ�����


	List<T> list = findPage(criteria, pageSize, pageNo);



	return new Page<T>(startIndex, totalCount, pageSize, list);

	}


}
