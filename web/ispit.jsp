<%--
    Document   : ispit
    Created on : Nov 4, 2015, 01:49:11 AM
    Author     : Drazen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Unos ocena</title>
  </head>
  <body>
    <h3>${messageisp}</h3>
    <table border="2">
      <tr><th>Šifra ispita</th><th>Ocena</th><th>Datum ispita</th></tr>
          <c:forEach items="${ocene1}" var="i" varStatus="status">
            <c:choose>
                  <c:when test="${status.index==crveno}"> <tr style="color:red"> </c:when>
          <c:otherwise> <tr></c:otherwise>
          </c:choose>
          <td>${i.sifra}</td>
          <td>${i.ocena}</td>
          <td>${i.datum}</td>
        </tr>
      </c:forEach>
    </table>
    <br/><br/>
    <a href="start.jsp">Nazad</a>
  </body>
</html>
