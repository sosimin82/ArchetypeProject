package com.op.template.interceptor;

import org.slf4j.MDC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextHierarchy({
@ContextConfiguration("/spring/applicationContext.xml"),
@ContextConfiguration("/spring/servlet-context.xml") })
public class SampleInterceptorTest {
	
	private ModelAndView modelAndView;
	
	@Test
	public void testHandles() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("userNo", "admin");
		MockHttpServletResponse response = new MockHttpServletResponse();
        
		SampleInterceptor sampleInterceptor = new SampleInterceptor();
		sampleInterceptor.preHandle(request, response, null);
		assertThat((String)MDC.get("userNo"), equalTo("admin"));
		sampleInterceptor.postHandle(request, response, null, modelAndView);
	}
	

}
