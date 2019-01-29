<%--
  Created by IntelliJ IDEA.
  User: luosen
  Date: 2019/1/14
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Login$</title>
  </head>
  <script type="text/javascript" language="JavaScript">
    function changeValidate() {
      var image=document.getElementById("imageId");
      image.src="/Login_Web_exploded/validate?"+new Date().getTime();
    }
  </script>
  <%
    String name = "";
    String passwd = "";
      if (request.getCookies() != null) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
          if ("userName".equals(cookies[i].getName())) {
            name = cookies[i].getValue();
          } else if ("userPasswd".equals(cookies[i].getName())) {
            passwd = cookies[i].getValue();
          }
        }
    }
  %>
  <body>
  <div style="margin: 20px">
    <form action="login" method="get">
      <table align="center" border="1px solid black">
        <tr>
          <td align="right">账号：</td>
          <td align="left"><input type="text" name="user" value="<%=name%>" id="user"></td>
        </tr>
        <tr>
          <td align="right">密码：</td>
          <td align="left"><input type="password" name="passwd" value="<%=passwd%>" id="passwd"></td>
        </tr>
        <tr>
          <td align="right">验证码：</td>
          <td align="left" style="vertical-align: middle"><input type="text" id="inputValidate" name="inputValidate">&nbsp&nbsp<img src="/Login_Web_exploded/validate" id="imageId">&nbsp&nbsp<a href="#" onclick="changeValidate()">看不清，换一张</a></td>
        </tr>
        <%

        %>
        <tr style="visibility: hidden">
          <td colspan="2" align="center" style="color: red">验证码错误</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="checkbox" name="check" value="1">记住密码</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><button type="submit">登录</button><button type="button" onclick="javascript:window.location.href='register.jsp'">注册</button></td>
        </tr>
      </table>
    </form>
  </div>
  </body>
</html>
