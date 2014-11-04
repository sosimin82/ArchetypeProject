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
<title>nbaseArc</title>
</head>
<body>

<%@ include file="/WEB-INF/view/top.jsp" %>

  Saved Data : ${msg} <input type="button" value="put data into Redis" onClick="javascript:location.href='redis'"/>
 
<%@ include file="/WEB-INF/view/bottom.jsp" %>

</body>
</html>