<%--
    Document   : ocene
    Created on : Nov 4, 2015, 5:49:16 AM
    Author     : Drazen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ocene</title>
  </head>
  <body>
    <c:if test="${not empty ocene}">
      Ocene studenata: <br/>
      <table>
        <tr><th>Ime</th><th>Prezime</th><th>Šifra</th><th>Ocena</th><th>Datum</th></tr>
            <c:forEach items="${ocene}" var="i">
          <tr>
            <td>${i.ime}</td>
            <td>${i.prezime}</td>
            <td>${i.sifra}</td>
            <td>${i.ocena}</td>
            <td>${i.datum}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <c:if test="${empty ocene}">
      Ne postoje ocene za zadati kriterijum.
    </c:if>
    <br/><br/>
    <a href="start.jsp">Nazad</a>
  </body>
</html>
