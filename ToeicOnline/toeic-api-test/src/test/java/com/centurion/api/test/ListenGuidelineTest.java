package com.centurion.api.test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.centurion.core.dao.ListenGuidelineDao;
import com.centurion.core.daoimpl.ListenGuidelineDaoImpl;

public class ListenGuidelineTest {

	ListenGuidelineDao listenGuidelineDao;

	@BeforeTest
	public void initData() {
		listenGuidelineDao = new ListenGuidelineDaoImpl();
	}

	@Test
	public void testFindByProperties() {
//		ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();

//		Object[] result = listenGuidelineDao.findByProperty(null, null, null, null, 0, 6);
	}

	@Test
	public void checkApiFindByPropties() {
		Map<String, Object> property = new HashMap<String, Object>();
		property.put("title", "Bài hd 8");
		property.put("content", "Nội dung bài hd 8");

		Object[] objects = listenGuidelineDao.findByProperty(property, null, null, null, null);
	}

}
