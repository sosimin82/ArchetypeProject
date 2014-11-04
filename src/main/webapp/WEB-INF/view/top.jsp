<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<a href="/main" class="N=a:archetype.main"> <spring:message code="top.main" /> </a> | 
<a href="/contents-list" class="N=a:archetype.list"> <spring:message code="top.list"/> </a> | 
<a href="/rest/contents-list" class="N=a:archetype.rest_list"><spring:message code="top.list_rest"/></a> | 
<a href="#" id="pop_list" class="N=a:archetype.rest_pop"> <spring:message code="top.json_list"/> </a>|  
<a href="/write-view" class="N=a:archetype_view"> <spring:message code="top.write"/> </a> | 
<a href="/rest/write-view" class="N=a:archetype.write_view"> <spring:message code="top.write_rest"/> </a> 
&nbsp;
<select name="lang">
   <option value=""><spring:message code="top.select"/></option>
   <option value="ko_KR"><spring:message code="top.korean"/></option>
   <option value="en_US"><spring:message code="top.english"/></option>
</select>

<br />
<br />