package com.centurion.core.data.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.centurion.core.commons.constant.CoreConstant;
import com.centurion.core.commons.utils.HibernateUtil;
import com.centurion.core.data.dao.GerericDao;

import javassist.tools.rmi.ObjectNotFoundException;

public class AbstractDao<ID extends Serializable, T> implements GerericDao<ID, T> {

	public AbstractDao() {

		this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	private Class<T> persistenceClass;

	public String getPersistenceClassName() {
		return persistenceClass.getSimpleName();

	}

	public Class<T> getPersistenceClass() {
		return persistenceClass;
	}

	public void setPersistenceClass(Class<T> persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	/*
	 * protected Session getSession() {
	 * 
	 * return HibernateUtil.getSessionFactory().openSession(); }
	 */

	@Override
	public List<T> findAll() {

		List<T> list = new ArrayList<T>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			// HQL;
			StringBuilder sql = new StringBuilder("from ");
			sql.append(this.getPersistenceClassName());
			Query query = session.createQuery(sql.toString());
			list = query.list();
			transaction.commit();
		} catch (HibernateException e) {

			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public T update(T entity) {

		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Object object = session.merge(entity);
			result = (T) object;
			transaction.commit();
			// HQL;
		} catch (HibernateException e) {

			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public void save(T entity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.persist(entity);

			transaction.commit();
			// HQL;
		} catch (HibernateException e) {

			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public T findId(ID id) {

		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = session.get(persistenceClass, id);

			if (result == null) {
				throw new ObjectNotFoundException(" " + id, null);
			}

			transaction.commit();
			// HQL;
		} catch (HibernateException e) {

			transaction.rollback();
			throw e;
		} catch (ObjectNotFoundException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

//findByProperty trả về số bản ghi và danh sách

	List<T> list = new ArrayList<T>();

	@Override
	public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Object totalItem = 0;
		try {
			transaction = session.beginTransaction();

			StringBuilder sql1 = new StringBuilder("from ");
			sql1.append(getPersistenceClassName());

			if (property != null && value != null) {
				sql1.append(" where ").append(property).append("= :value");
			}
			if (sortExpression != null && sortDirection != null) {
				sql1.append(" order by ").append(sortExpression);
				sql1.append(" " + (sortDirection.equals(CoreConstant.SORT_ASC) ? "asc" : "desc"));
			}
			Query query1 = session.createQuery(sql1.toString());

			if (value != null) {
				query1.setParameter("value", value);
			}

			if (offset != null && offset >= 0) {

				query1.setFirstResult(offset);
			}

			if (limit != null && limit > 0) {
				query1.setMaxResults(limit);
			}

			list = query1.list();

			StringBuilder sql2 = new StringBuilder("select count(*) from ");
			sql2.append(getPersistenceClassName());

			if (property != null && value != null) {
				sql2.append(" where ").append(property).append("= :value");
			}

			Query query2 = session.createQuery(sql2.toString());
			if (value != null) {
				query2.setParameter("value", value);
			}
			totalItem = query2.list().get(0);

			transaction.commit();
			// HQL;
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;

		} finally {
			session.close();
		}
		return new Object[] { totalItem, list };
	}

	@Override
	public Integer delete(List<ID> ids) {

		Integer count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		try {

			for (ID id : ids) {

				T t = session.get(persistenceClass, id);
				session.delete(t);
				count++;
			}

			transaction.commit();
			// HQL;
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
		return count;

	}

}
