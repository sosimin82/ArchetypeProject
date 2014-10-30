<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>
</head>
<body>

<%@ include file="/WEB-INF/view/top.jsp" %>

  seq : ${sampleContents.seq} / msg : ${sampleContents.msg} 
  <br />
  <br />
  <input type="button" value="수정" onClick="javascript:location.href='modify-view?seq=${sampleContents.seq}'"/>
  <input type="button" value="삭제" onClick="javascript:location.href='remover?seq=${sampleContents.seq}'"/>

<%@ include file="/WEB-INF/view/bottom.jsp" %>

</body>
</html>