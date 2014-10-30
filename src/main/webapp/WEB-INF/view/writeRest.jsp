<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeRest</title>
</head>
<body>

<%@ include file="/WEB-INF/view/top.jsp" %>

<form name="mainFrm">
  msg : <input type="text" name="msg" value="${sampleContents.msg}"/> <input type="button" id="save" value="저장"/>
</form>

<script>
    //nclick에서 서비스를 식별하기 위한 변수
	var nsc = "archetype.writeRest";
    
    var obj = new Object();
    <c:if test="${not empty sampleContents.seq}">
       obj.seq = "${sampleContents.seq}";
    </c:if>
</script>

<%@ include file="/WEB-INF/view/bottom.jsp" %>

</body>
</html>

