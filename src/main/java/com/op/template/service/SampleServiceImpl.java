package com.op.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.template.dao.SampleDao;
import com.op.template.model.SampleContents;
//import com.op.template.util.NBaseArcMap;

/**
 * @author NHNEnt
 */
@Service
public class SampleServiceImpl implements SampleService {
	@Autowired 
	SampleDao sampleDao;
	
//	@Autowired
//	NBaseArcMap nbaseArcCache;


	public String doDbTest() {
//		if (nbaseArcCache.hasValueForKey("msg")) {
//			return (String)nbaseArcCache.getValueFromNBaseArc("msg");
//		} else {
			return sampleDao.testDbSelect();
//		}
	}

	public List<SampleContents> getSampleContentsList() {
		return sampleDao.selectSampleContentsList();
	}

	public SampleContents getSampleContents(int seq) {
		return sampleDao.selectSampleContents(seq);
	}

	public int createSampleContens(SampleContents sampleContents) {
		return sampleDao.insertSampleContens(sampleContents);
	}

	public int modifySampleContents(SampleContents sampleContents) {
		return sampleDao.updateSampleContents(sampleContents);
	}

	public int removeSampleContens(int seq) {
		return sampleDao.deleteSampleContens(seq);
	}

	public int testCreateSampleContens(SampleContents sampleContents) {
		return sampleDao.insertTestSampleContens(sampleContents);
	}

}
