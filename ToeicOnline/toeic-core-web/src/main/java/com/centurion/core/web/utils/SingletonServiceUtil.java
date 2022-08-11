package com.centurion.core.web.utils;

import com.centurion.core.service.impl.ListenGuidelineServiceImpl;
import com.centurion.core.service.impl.RoleServiceImpl;
import com.centurion.core.service.impl.UserServiceImpl;

public class SingletonServiceUtil {
	private static UserServiceImpl userServiceImpl = null;
	private static RoleServiceImpl roleServiceImpl = null;
	private static ListenGuidelineServiceImpl listenGuidelineServiceImpl = null;

	public static UserServiceImpl getUserServiceInstance() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}

	public static RoleServiceImpl getRoleServiceInstance() {
		if (roleServiceImpl == null) {
			roleServiceImpl = new RoleServiceImpl();
		}
		return roleServiceImpl;
	}

	public static ListenGuidelineServiceImpl getListenGuidelineServiceInstance() {
		if (listenGuidelineServiceImpl == null) {
			listenGuidelineServiceImpl = new ListenGuidelineServiceImpl();
		}
		return listenGuidelineServiceImpl;
	}
}
