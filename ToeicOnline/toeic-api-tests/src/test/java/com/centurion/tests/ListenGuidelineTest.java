package com.centurion.tests;

import org.testng.annotations.Test;

import com.centurion.core.dao.ListenGuidelineDao;
import com.centurion.core.daoimpl.ListenGuidelineDaoImpl;

public class ListenGuidelineTest {

	@Test
	public void testFindByProperties() {
		ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();

		Object[] result = listenGuidelineDao.findByProperty(null, null, null, null, 0, 6);
	}

}
