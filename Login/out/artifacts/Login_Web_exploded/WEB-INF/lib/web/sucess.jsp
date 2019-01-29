<%--
  Created by IntelliJ IDEA.
  User: luosen
  Date: 19-1-17
  Time: 下午1:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<%
    String flag=(String)request.getAttribute("flag");
    String name=(String)request.getAttribute("name");
    String user=(String) request.getSession().getAttribute("user");
    if (user!=null){
%>
<div align="center" style="margin: 20px;color: red"><span>当前用户：<%=user%></span>&nbsp;&nbsp;<%}%><%=flag%><%if (flag.equals("用户名已存在")){%>
    <a href="register.jsp">重新注册</a>
    <%}%>
    <%if(flag.equals("登录成功")){%>
    &nbsp;&nbsp;<a href="loginout?name=<%=name%>">注销登录</a>
    <%}%>
</div>
<hr style="color: black">
<div name="coperation" style="display:inline" align="center">
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" id="file">
        <input type="submit" value="上传">
    </form>
    <form action="searsh" method="post">
        <input type="text" name="searchById">
        <input type="submit" value="查询">
    </form>
</div>
<%
    String message=(String) request.getAttribute("message");
    if(message!=null&&!message.equals("")){
%>
<div align="center"><h3 style="color: black"><%=message%>></h3></div>
<%}%>
<div name="showFile" align="center">
<table>
    <tr>
        <th>编号</th>
        <th>图片</th>
        <th>上传时间</th>
        <th>操作</th>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>
</div>
</body>
</html>
