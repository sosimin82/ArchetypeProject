package com.op.template.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.op.template.dao.SampleDao;
import com.op.template.model.SampleContents;
import com.op.template.service.SampleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceTest {
    
	
	@Mock
	private SampleDao sampleDao;
	
	
	@InjectMocks
    private SampleServiceImpl sampleBo;
    
    @Test
    public void doDbTest() throws Exception {
    	when(sampleDao.testDbSelect()).thenReturn("Test Msg");
    	String resultMsg = sampleBo.doDbTest();
    	
    	verify(sampleDao).testDbSelect();
    	assertThat(resultMsg, equalTo("Test Msg"));
    }
    
    @Test
    public void getSampleContentsList() throws Exception {
    	SampleContents sampleContents = new SampleContents();
    	sampleContents.setSeq(0);
    	sampleContents.setMsg("test");
    	List<SampleContents> sampleContentsList = new ArrayList<SampleContents>();
    	sampleContentsList.add(sampleContents);
    	
    	when(sampleDao.selectSampleContentsList()).thenReturn(sampleContentsList);
    	
    	List<SampleContents> returnedSampleContentsList = sampleBo.getSampleContentsList();
    	verify(sampleDao).selectSampleContentsList();
    	
    	SampleContents returnedSampleContents = returnedSampleContentsList.get(0);
    	assertThat(returnedSampleContents.getMsg(), equalTo("test"));
    }
    
    @Test
    public void getSampleContents() throws Exception {
    	SampleContents sampleContents = new SampleContents();
    	sampleContents.setSeq(0);
    	sampleContents.setMsg("test");
    	
    	when(sampleDao.selectSampleContents(0)).thenReturn(sampleContents);
    	
    	SampleContents returnedSampleContents = sampleBo.getSampleContents(0);
    	verify(sampleDao).selectSampleContents(0);
    	
    	assertThat(returnedSampleContents.getMsg(), equalTo("test"));
    }
    
    @Test
    public void createSampleContents() throws Exception {
    	SampleContents sampleContents = new SampleContents();
    	sampleContents.setMsg("test");
    	
    	when(sampleDao.insertSampleContens(sampleContents)).thenReturn(1);
    	
    	int executeCount = sampleBo.createSampleContens(sampleContents);
    	verify(sampleDao).insertSampleContens(sampleContents);
    	
    	assertThat(executeCount, equalTo(1));
    }
    
    @Test
    public void modifySampleContents() throws Exception {
    	SampleContents sampleContents = new SampleContents();
    	sampleContents.setSeq(0);
    	sampleContents.setMsg("test");
    	
    	when(sampleDao.updateSampleContents(sampleContents)).thenReturn(1);
    	
    	int executeCount = sampleBo.modifySampleContents(sampleContents);
    	verify(sampleDao).updateSampleContents(sampleContents);
    	
    	assertThat(executeCount, equalTo(1));
    }
    
    @Test
    public void removeSampleContents() throws Exception {
    	when(sampleDao.deleteSampleContens(0)).thenReturn(1);
    	
    	int executeCount = sampleBo.removeSampleContens(0);
    	verify(sampleDao).deleteSampleContens(0);
    	
    	assertThat(executeCount, equalTo(1));
    }
    
}
