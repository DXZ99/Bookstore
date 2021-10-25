<%--
  Created by IntelliJ IDEA.
  User: zhaodongxiao
  Date: 2020/12/20
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--用于动态包含base标签、css样式、jquery文件--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);

%>
<base href="<%=basePath%>" />
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>