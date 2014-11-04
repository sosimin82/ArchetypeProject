package com.op.template.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.op.template.controller.SampleController;
import com.op.template.model.SampleContents;
import com.op.template.service.SampleService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextHierarchy({
@ContextConfiguration("/spring/applicationContext.xml"),
@ContextConfiguration("/spring/servlet-context.xml") })
public class SampleControllerTest {
	// Mock 객체 선언
	@Mock
	private SampleService sampleService;
	
	@Mock
	private SampleContents sampleContents;

	// 위에서 선언된 Mock 객체를 @Autowired 선언된 객체에서  불러쓸 수 있도록 선언
	@InjectMocks
	private SampleController sampleController = new SampleController();
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
	}
	
	@Test
	public void testMain() throws Exception {
		when(sampleService.doDbTest()).thenReturn("this is Data from DB");
		
		mockMvc.perform(get("/main"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("sample"))
		       .andExpect(model().attributeExists("msg"));
		
		verify(sampleService).doDbTest();
	}
	
	@Test
	public void viewWrite() throws Exception {
		mockMvc.perform(get("/write-view"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("write"));
	}
	
	@Test
	public void viewModify() throws Exception {
		mockMvc.perform(get("/modify-view").param("seq", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("write"));
	}
	
	@Test
	public void getContents() throws Exception {
		when(sampleService.getSampleContents(0)).thenReturn(sampleContents);
		
		mockMvc.perform(get("/viewer").param("seq", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("view"))
		.andExpect(model().attributeExists("sampleContents"));
		
		verify(sampleService).getSampleContents(0);
	}
	
	@Test
	public void createContents() throws Exception {
		mockMvc.perform(post("/creator").param("msg", "test"))
		.andExpect(status().isOk())
		.andExpect(view().name("list"));
	}
	
	@Test
	public void modifyContents() throws Exception {
		mockMvc.perform(post("/modifier")
				.param("seq", "1")
				.param("msg", "test"))
				.andExpect(status().isOk())
				.andExpect(view().name("list"));
	}
	
	@Test
	public void getContentList() throws Exception {
		mockMvc.perform(get("/contents-list"))
		.andExpect(status().isOk())
		.andExpect(view().name("list"));
		verify(sampleService).getSampleContentsList();
	}
	
	@Test
	public void removeContents() throws Exception {
		when(sampleService.removeSampleContens(0)).thenReturn(1);
		mockMvc.perform(get("/remover").param("seq", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("list"));
		verify(sampleService).removeSampleContens(0);
	}
}
