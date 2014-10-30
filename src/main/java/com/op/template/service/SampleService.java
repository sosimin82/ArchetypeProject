package com.op.template.service;

import java.util.List;

import com.op.template.model.SampleContents;

/**
 * @author NHNEnt
 */
public interface SampleService {
	String doDbTest();
	
    List<SampleContents> getSampleContentsList();
    SampleContents getSampleContents(int seq);
    int createSampleContens(SampleContents sampleContents);
    int modifySampleContents(SampleContents sampleContents);
    int removeSampleContens(int seq);
    int testCreateSampleContens(SampleContents sampleContents);
}
