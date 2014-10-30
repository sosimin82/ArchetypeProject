package com.op.template.dao;

import java.util.List;

import com.op.template.model.SampleContents;

public interface SampleDao {
    String testDbSelect();
    
    List<SampleContents> selectSampleContentsList();
    SampleContents selectSampleContents(int seq);
    int insertSampleContens(SampleContents sampleContents);
    int updateSampleContents(SampleContents sampleContents);
    int deleteSampleContens(int seq);
    int insertTestSampleContens(SampleContents sampleContents);
    
}
