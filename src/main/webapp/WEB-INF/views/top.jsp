<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="/main"> <fmt:message key="top.main"/> </a> | 
<a href="/list"> <fmt:message key="top.list"/> </a> | 
<a href="/write-view" class="N=a:archetype_view"> <fmt:message key="top.write"/> </a> | 
&nbsp;
<select name="lang">
   <option value=""><fmt:message key="top.select"/></option>
   <option value="kr"><fmt:message key="top.korean"/></option>
   <option value="en"><fmt:message key="top.english"/></option>
</select>

<br />
<br />