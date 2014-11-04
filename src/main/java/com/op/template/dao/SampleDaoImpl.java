package com.op.template.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.op.template.model.SampleContents;

@Repository
public class SampleDaoImpl implements SampleDao {
	private static final String NAMESPACE = SampleDaoImpl.class.getPackage().getName() + ".SampleDao.";
	
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(SampleDaoImpl.class);
	
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    
	public String testDbSelect() {
		return (String)sqlSessionTemplate.selectOne(NAMESPACE + "selectTest");
	}

	@Cacheable(value = "test")
	public SampleContents selectSampleContents(int seq) {
		LOGGER.info("this is db data");
		return sqlSessionTemplate.selectOne(NAMESPACE + "selectMsg", seq);
	}
	
	public List<SampleContents> selectSampleContentsList() {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectMsgList");
	}

	public int insertSampleContens(SampleContents sampleContents) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertMsg", sampleContents);
	}

	@CacheEvict(value = "test", key = "#sampleContents.seq")
	public int updateSampleContents(SampleContents sampleContents) {
		return sqlSessionTemplate.update(NAMESPACE + "updateMsg", sampleContents);
	}

	@CacheEvict(value = "test")
	public int deleteSampleContens(int seq) {
		return sqlSessionTemplate.delete(NAMESPACE + "deleteMsg", seq);
	}

	public int insertTestSampleContens(SampleContents sampleContents) {
		return sqlSessionTemplate.insert(NAMESPACE + "testInsertMsg", sampleContents);
	}

}
