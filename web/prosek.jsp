<%--
    Document   : prosek
    Created on : Nov 4, 2015, 04:29:51 AM
    Author     : Drazen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Prosek</title>
  </head>
  <body>
    <c:if test="${imastudent}">
      Student: ${student.ime}<br/>
      Indeks: ${student.indeks}<br/>
      Prosek: ${student.prosek}<br/>
    </c:if>

    <h2>${message}</h2>

    <br/>
    <a href="start.jsp">Nazad</a>
  </body>
</html>
