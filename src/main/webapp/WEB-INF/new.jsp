<%@ page import="servlet.Client" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.10.2015
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>my table</title>
</head>
<body>
<table border="1">
  <tr>
    <th>id</th>
    <th>FirstName</th>
    <th>LastName</th>
    <th>Adress</th>

  </tr>
  <tr>
    <%

      for (Client cl : (List<Client>) request.getAttribute("list")) {
    %>
    <td><%=cl.getClient_id()%></td>
    <td><%=cl.getFirstName()%></td>
    <td><%=cl.getLastName()%></td>
    <td><%=cl.getAddres()%></td>



  </tr>
  <%
    }

  %>
</table>
<a href="/edit">Edit record intdo DB</a>
</body>
</html>
