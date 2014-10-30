<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>write</title>
</head>
<body>

<%@ include file="/WEB-INF/view/top.jsp" %>


<c:if test="${not empty sampleContents.seq}">
    <form name="mainFrm" action="modifier" method="post">
</c:if>
<c:if test="${empty sampleContents.seq}">
    <form name="mainFrm" action="creator" method="post">
</c:if>

<c:if test="${not empty sampleContents.seq}">
             <input type="hidden" name="seq" value="${sampleContents.seq}" />
</c:if>
  msg : <input type="text" name="msg" value="${sampleContents.msg}"/> <input type="submit" value="저장"/>
</form>

<script>
    //nclick에서 서비스를 식별하기 위한 변수
	var nsc = "archetype.write";
</script>
<%@ include file="/WEB-INF/view/bottom.jsp" %>

</body>
</html>