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
  <body>
  <div style="margin: 20px">
    <form action="" method="get">
      <table align="center" border="1px solid black">
        <tr>
          <td align="right">账号：</td>
          <td align="left"><input type="text" name="user"></td>
        </tr>
        <tr>
          <td align="right">密码：</td>
          <td align="left"><input type="password" name="user"></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="radio" name="check" value="1">记住密码</td>
        </tr>
        <tr>
          <td colspan="2" align="center"><button type="submit">登录</button><button type="button" onclick="javascript:window.location.href='register.jsp'">注册</button></td>
        </tr>
      </table>
    </form>
  </div>
  </body>
</html>
