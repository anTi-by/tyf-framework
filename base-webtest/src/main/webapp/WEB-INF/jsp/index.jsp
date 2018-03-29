
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/sso/login" method="post">
    <span>用户名</span> <input id="account" type="text" name="account"/>
    <span>密码</span><input id="password" type="text" name="password"/>
    <input id="" type="hidden" name="gotoUrl" value="${gotoUrl}"/>
    <button type="submit" >提交</button>
</form>
</body>
</html>
