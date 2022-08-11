package com.centurion.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.centurion.core.commons.utils.HibernateUtil;
import com.centurion.core.dao.UserDao;
import com.centurion.core.data.daoimpl.AbstractDao;
import com.centurion.core.persistence.entity.UserEntity;

/**
 * Created by Admin on 9/7/2017.
 */
public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {
	@Override
	public Object[] checkLogin(String name, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUserExist = false;
		String roleName = null;
		try {
			StringBuilder sql = new StringBuilder(
					" FROM UserEntity ue WHERE ue.name= :name AND ue.password= :password");
			Query query = session.createQuery(sql.toString());
			query.setParameter("name", name);
			query.setParameter("password", password);
			if (query.list().size() > 0) {
				isUserExist = true;
				UserEntity userEntity = (UserEntity) query.uniqueResult();
				roleName = userEntity.getRoleEntity().getName();
			}
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
		return new Object[] { isUserExist, roleName };
	}
}
