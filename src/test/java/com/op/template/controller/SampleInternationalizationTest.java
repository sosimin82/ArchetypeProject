package com.op.template.controller;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
@ContextConfiguration("/spring/applicationContext.xml"),
@ContextConfiguration("/spring/servlet-context.xml") })
public class SampleInternationalizationTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Test
	public void testChangeLocale() throws Exception {
		//Spring의 Context를 이용하여 locale을 변경하였을때 화면에 가져오는 메시지를 테스트 하기 위함
		
		// 한국어를 사용하는 브라우저에서 default locale을 이용하여 메시지를 가져올 경우
		Locale localeDefault = Locale.getDefault();
		String stringValue = wac.getMessage("top.list", new Object[0], localeDefault);
		assertThat("리스트", is(stringValue));
		// parameter로 ko_KR을 이용하여 화면의 메뉴 message를 가져올 경우
		Locale localeKo_KR = new Locale("ko_KR");
		stringValue = wac.getMessage("top.list", new Object[0], localeKo_KR);
		assertThat("리스트", is(stringValue));
		// parameter로 en_US을 이용하여 화면의 메뉴 message를 가져올 경우
		Locale localeEn_US = new Locale("en_US");
		stringValue = wac.getMessage("top.list", new Object[0], localeEn_US);
		assertThat("List", is(stringValue));
	}
	
	
}
