package com.op.template.dao;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.op.template.dao.SampleDao;
import com.op.template.model.SampleContents;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
@ContextConfiguration("/spring/applicationContext.xml"),
@ContextConfiguration("/spring/servlet-context.xml") })
public class SampleDaoTest {
	@Autowired
	private SampleDao sampleDao;
	private SampleContents sampleContents;
	
	@Before
	public void setUp() throws Exception {
		sampleContents = new SampleContents();
		sampleContents.setSeq(0);
		sampleContents.setMsg("test");
		assertThat(sampleDao.insertTestSampleContens(sampleContents), equalTo(1));
	}

	@After
	public void clean() throws Exception {
		assertThat(sampleDao.deleteSampleContens(0), equalTo(1));
	}
	
	@Test
	public void dbTestSelect() throws Exception {
		assertThat(sampleDao.testDbSelect(), equalTo("This is Data from DB"));
	}

	@Test
	public void selectSampleContents() throws Exception {
		assertThat(sampleDao.selectSampleContents(0).getMsg(), equalTo("test"));
		
	}

	@Test
	public void selectSampleContentsList() throws Exception {
		assertTrue(sampleDao.selectSampleContentsList().size() > 0);
	}
	@Test
	public void updateSampleContents() throws Exception {
		sampleContents.setSeq(0);
		sampleContents.setMsg("test_1");
		assertThat(sampleDao.updateSampleContents(sampleContents), equalTo(1));
	}

}
