<%--
    Document   : error
    Created on : Nov 4, 2015, 01:37:07 AM
    Author     : Drazen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
  </head>
  <body>
    <h2>Dogodila se greška!</h2>
    ${message}
  </body>
</html>
