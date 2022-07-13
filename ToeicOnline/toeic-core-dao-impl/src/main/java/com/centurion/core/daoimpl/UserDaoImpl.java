package com.centurion.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.centurion.core.commons.utils.HibernateUtil;
import com.centurion.core.dao.UserDao;
import com.centurion.core.data.daoimpl.AbstractDao;
import com.centurion.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

	/*
	 * @Override public UserEntity isUserExist(String name, String password) {
	 * 
	 * return findUserByNameAndPassword(name, password); }
	 * 
	 * @Override public UserEntity findRoleByUser(String name, String password) {
	 * 
	 * return findUserByNameAndPassword(name, password); }
	 */

	@Override
	public UserEntity findUserByNameAndPassword(String name, String password) {
		UserEntity entity = new UserEntity();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name= :name AND password= :password");
			Query query = session.createQuery(sql.toString());
			query.setParameter("name", name);
			query.setParameter("password", password);
			entity = (UserEntity) query.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

}
