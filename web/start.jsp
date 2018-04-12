<%--
    Document   : start
    Created on : Nov 4, 2015, 06:05:56 AM
    Author     : Drazen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lab2</title>
  </head>
  <body>
    <font color="red">${messageprosek}</font>

    <form action="prosek" method="POST">
      Broj indeksa: <input type="text" name="indeks" value="${student.indeks}"/><br/><br/>
      <input type="submit" value="Nadji prosek"/><br/>
    </form>
    <br/><hr>
    <font color="red">${messagedat}</font>

    <form action="ocene" method="POST">
      <table>
        <tr><td>Prvi datum: </td>
          <td>
            <select name="dan1">
              <c:forEach begin="1" end="31" step="1" var="i">
                <c:if test="${i == datum.dan1}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.dan1}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="mesec1">
              <c:forEach begin="1" end="12" step="1" var="i">
                <c:if test="${i == datum.mesec1}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.mesec1}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="god1">
              <c:forEach begin="2000" end="2016" step="1" var="i">
                <c:if test="${i == datum.godina1}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.godina1}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>

            </select>
          </td>
        </tr>
        <tr><td>Drugi datum: </td>
          <td>
            <select name="dan2">
              <c:forEach begin="1" end="31" step="1" var="i">
                <c:if test="${i == datum.dan2}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.dan2}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="mesec2">
              <c:forEach begin="1" end="12" step="1" var="i">
                <c:if test="${i == datum.mesec2}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.mesec2}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
          <td>
            <select name="god2">
              <c:forEach begin="2000" end="2016" step="1" var="i">
                <c:if test="${i == datum.godina2}">
                  <option  value="${i}" selected>${i}</option>
                </c:if>
                <c:if test="${i != datum.godina2}">
                  <option  value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
        </tr>
      </table>
      <br/>
      <input type="submit" value="Pronadji ocene"/>
    </form>

    <br/><hr>

    <font color="red">${messageisp}</font>

    <form action="ispit" method="POST">
      <table>
        <tr>
          <td>Godina upisa:</td>
          <td>
            <input type="text" size="4" maxlength="4" name="godina" value="${unos.godina}"/>
          </td>
        </tr>
        <tr>
          <td>Broj indeksa:</td>
          <td>
            <input type="text" size="4" maxlength="4" name="broj" value="${unos.broj}"/>
          </td>
        </tr>
        <tr>
          <td>Šifra predmeta:</td>
          <td>
            <input type="text" size="6" maxlength="6" name="sifra" value="${unos.sifra}"/>
          </td>
        </tr>
        <tr>
          <td>Ocena:</td>
          <td>
            <select name="ocena">
              <c:forEach begin="5" end="10" step="1" var="i">
                <c:if test="${unos.ocena==i}">
                  <option selected value="${i}">${i}</option>
                </c:if>
                <c:if test="${unos.ocena!=i}">
                  <option value="${i}">${i}</option>
                </c:if>
              </c:forEach>
            </select>
          </td>
        </tr>
      </table>
      <input type="submit" value="Unesi ocenu"/>
    </form>
  </body>
</html>
