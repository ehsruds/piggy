<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.UserInfoDTO" %>
<%

String msg = CmmUtil.nvl((String)request.getAttribute("msg"));


UserInfoDTO pDTO = (UserInfoDTO)request.getAttribute("pDTO");

if (pDTO==null){
	pDTO = new UserInfoDTO();
	
}

%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지.</title>
<script type="text/javascript">
	window.alert("<%=msg%>");
</script>
</head>
<body>
<%=CmmUtil.nvl(pDTO.getUser_name()+ "만들었습니다")%>
</body>
</html>