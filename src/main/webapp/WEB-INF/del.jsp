<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.10.2015
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Delete from DB</title>
</head>
<body>
<form action="/del" method="get">
  Input name<input type="text" name="name"/>
  <input type="submit" name="Ок" value="Ок"/>

</form>
<a href="/servlets">list</a>
</body>
</html>
