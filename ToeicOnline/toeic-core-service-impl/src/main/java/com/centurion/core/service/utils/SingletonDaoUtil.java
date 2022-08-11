package com.centurion.core.service.utils;

import com.centurion.core.daoimpl.ListenGuidelineDaoImpl;
import com.centurion.core.daoimpl.RoleDaoImpl;
import com.centurion.core.daoimpl.UserDaoImpl;

public class SingletonDaoUtil {
	private static UserDaoImpl userDaoImpl = null;
	private static RoleDaoImpl roleDaoImpl = null;
	private static ListenGuidelineDaoImpl listenGuidelineDaoImpl = null;

	public static UserDaoImpl getUserDaoInstance() {
		if (userDaoImpl == null) {
			userDaoImpl = new UserDaoImpl();
		}
		return userDaoImpl;
	}

	public static RoleDaoImpl getRoleDaoInstance() {
		if (roleDaoImpl == null) {
			roleDaoImpl = new RoleDaoImpl();
		}
		return roleDaoImpl;
	}

	public static ListenGuidelineDaoImpl getListenGuidelineDaoInstance() {
		if (listenGuidelineDaoImpl == null) {
			listenGuidelineDaoImpl = new ListenGuidelineDaoImpl();
		}
		return listenGuidelineDaoImpl;
	}
}
