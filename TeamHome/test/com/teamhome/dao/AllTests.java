package com.teamhome.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArticleDao.class, TestAttributeDao.class,
		TestLoginDao.class, TestMenuDao.class, TestMessageDao.class })
public class AllTests {

}
