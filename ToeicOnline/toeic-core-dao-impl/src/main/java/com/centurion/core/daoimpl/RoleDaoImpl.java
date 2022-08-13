package com.centurion.core.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.centurion.core.commons.utils.HibernateUtil;
import com.centurion.core.dao.RoleDao;
import com.centurion.core.data.daoimpl.AbstractDao;
import com.centurion.core.persistence.entity.RoleEntity;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {

	@Override
	public List<RoleEntity> findByRole(List<String> roles) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
		try {
			StringBuilder sql = new StringBuilder("FROM RoleEntity re where re.name IN (:roles) ");
			Query query = session.createQuery(sql.toString());
			query.setParameterList("roles", roles);// truyen list
			roleEntities = query.list();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		} finally {
			session.close();
		}
		return roleEntities;
	}
}
