<%--
  Created by IntelliJ IDEA.
  User: luosen
  Date: 2019/1/14
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="c" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<html>
<head>
    <title>register</title>
</head>
<script type="text/javascript" language="JavaScript">
    function check() {
        var userInput=document.getElementById("user");
        var passwdInput=document.getElementById("passwd");
        var passwordInput=document.getElementById("password");
        if (userInput.value==""||passwdInput.value==""||passwordInput.value==""){
            alert("输入不能为空！");
        }
        if(passwdInput.value!=passwordInput.value){
            alert("前后密码不一致！")
        }
        if(userInput.value!=null&&passwdInput.value!=null&&passwordInput.value!=null&&passwdInput.value==passwordInput.value) {
            document.getElementById("registerForm").submit();
        }
    }
</script>
<body>
<div style="margin: 20px"></div>
<form  action="register"  method="get" id="registerForm">
    <table align="center" border="1px solid black">
        <tr>
            <td align="right">账号：</td>
            <td align="left"><input type="text" name="user" id="user"></td>
        </tr>
        <tr>
            <td align="right">密码：</td>
            <td align="left"><input type="password" name="passwd" id="passwd"></td>
        </tr>
        <tr>
            <td align="right">确认密码：</td>
            <td align="left"><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><button type="button" onclick="check()">注册</button></td>
        </tr>
    </table>
</form>
</body>
</html>
