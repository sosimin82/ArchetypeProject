<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript" src="/js/lib/jquery/jquery-2.1.1.min.js"></script>

<script>
	//<![CDATA[
   
   $(function() {
	   $("#pop_list").click(function() {
		   window.open('/rest/list', '', "width=600, height=200, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no");	
	   });
	   
	   $("#save").click(function() {
		   var url = "creator";
		   var method = "POST";
		   
		   if (obj.seq != null) {
			   url = "modifier";
			   method = "PUT";
		   }
		   
		   obj.msg = document.mainFrm.msg.value;

		   var jsonStr = JSON.stringify(obj);
		   
		   var request = new XMLHttpRequest(); 
		   request.open(method, url, true); // 전달 방식 설정
		   request.onreadystatechange = goRestList; // 수행되고 난 후 결과 페이지를 호출하는 callback 함수 설정
		   request.setRequestHeader("Content-Type","application/json;charset=UTF-8"); // spring에서 설정한 type으로 선언
		   request.send(jsonStr); // 설정된 url로 parameter를 전달
	   });
	   
	   $("select[name=lang]").change(function() {
		   var urlStr = location.href.toString();
		   var endPosition = urlStr.indexOf("?") == -1 ? urlStr.length : urlStr.indexOf("?");

		   var url = urlStr.substring(0, endPosition).replace("#","");
		   
		   location.href = url + "?lang=" + $("select[name=lang]").val(); 
	   });
	});
   
   function goRestList(){
	   location.href = "list";
   }
	//]]>
</script>