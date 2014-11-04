package com.op.template.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.op.template.model.SampleContents;
import com.op.template.service.SampleService;

/**
 * @author NHNEnt
 *
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class SampleRestControllerTest {
	
	// Mock 객체 선언
	@Mock
	private SampleService sampleService;
	
	// 위에서 선언된 Mock 객체를 sampleRestController에서  불러쓸 수 있도록 선언
	@InjectMocks
	private SampleRestController sampleRestController;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(sampleRestController).build();
	}
	
	@Test
	public void getContentsTest() throws Exception {
		int seq = 1;
		SampleContents sampleContents = new SampleContents();
		sampleContents.setSeq(seq);
		sampleContents.setMsg("test");
		when(sampleService.getSampleContents(seq)).thenReturn(sampleContents);
		
		MvcResult result = mockMvc.perform(get("/rest/contents/1"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.header.serviceCode", equalTo(10)))
				.andDo(print())
				.andReturn();	
		String content = result.getResponse().getContentAsString();
		assertTrue(content.indexOf("header") > -1);
	}
	
	
	@Test
	public void creatorTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/rest/creator").contentType(MediaType.APPLICATION_JSON)
				.content("{\"msg\":\"test\"}"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.header.serviceCode", equalTo(10)))
				.andDo(print())
				.andReturn();	
		String content = result.getResponse().getContentAsString();
		assertTrue(content.indexOf("header") > -1);
	}
	
	
	@Test
	public void modifierTest() throws Exception {
		SampleContents sampleContents = new SampleContents();
		sampleContents.setSeq(1);
		sampleContents.setMsg("test");
		when(sampleService.getSampleContents(sampleContents.getSeq())).thenReturn(new SampleContents());
		
		MvcResult result = mockMvc.perform(put("/rest/modifier").contentType(MediaType.APPLICATION_JSON)
				.content("{\"seq\":1,\"msg\":\"test\"}"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.header.serviceCode", equalTo(10)))
				.andDo(print())
				.andReturn();	
		String content = result.getResponse().getContentAsString();
		assertTrue(content.indexOf("header") > -1);
	}

	
	@Test
	public void removeContentsTest() throws Exception {
		int seq = 1;
		when(sampleService.removeSampleContens(seq)).thenReturn(1);
		
		MvcResult result = mockMvc.perform(delete("/rest/remover/1"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.header.serviceCode", equalTo(10)))
				.andDo(print())
				.andReturn();	
		String content = result.getResponse().getContentAsString();
		assertTrue(content.indexOf("header") > -1);
	}
	
	
	@Test
	public void listTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/rest/list"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.header.serviceCode", equalTo(10)))
				.andDo(print())
				.andReturn();	
		String content = result.getResponse().getContentAsString();
		assertTrue(content.indexOf("header") > -1);
	}
}
