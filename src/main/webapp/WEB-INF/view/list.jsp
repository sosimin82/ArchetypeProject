<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>

<%@ include file="/WEB-INF/view/top.jsp" %>

<c:forEach var="sampleContents" items="${contentsList}">
   <a href="viewer?seq=${sampleContents.seq}">${sampleContents.seq} : ${sampleContents.msg}</a> <br />
</c:forEach>


<script>
    // nclick에서 서비스를 식별하기 위한 변수
	var nsc = "archetype.list";
</script>
<%@ include file="/WEB-INF/view/bottom.jsp" %>

</body>
</html>