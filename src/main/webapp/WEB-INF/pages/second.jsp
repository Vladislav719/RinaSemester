<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 29.12.2014
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:if test="${!empty listDepartments}">
  <form action="/info" method="post">
      <select name="department">
        <c:forEach items="${listDepartments}" var="w">
          <option value="${w}">${w}</option>
        </c:forEach>
        <input type="submit"/>
      </select>
  </form>
</c:if>

<c:if test="${!empty departmentInfo}">
  <table>
    <thead>
      <tr>
        <th>Rank</th>
        <th>Degree</th>
        <th>Post</th>
        <th>Name</th>
        <th>Department</th>
        <th>Place</th>
        <th>Institute</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="w" items="${departmentInfo}">
        <tr>
          <td>${w.rank}</td>
          <td>${w.degree}</td>
          <td>${w.post}</td>
          <td>${w.name}</td>
          <td>${w.department}</td>
          <td>${w.place}</td>
          <td>${w.institute}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>


<c:if test="${!empty listDepartments2}">
  Получить информацию о сотрудниках со следующего предприятия
  <form action="/departInfo" method="post">
    <select name="department">
      <c:forEach var="w" items="${listDepartments2}">
        <option value="${w}">${w}</option>
      </c:forEach>
    </select>
    <input type="submit">
  </form>
</c:if>

<c:if test="${!empty workerInfoList}">
  <table>
    <thead>
      <tr>
        <th>Rank</th>
        <th>Department</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Region</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="w" items="${workerInfoList}">
        <tr>
          <td>${w.rank}</td>
          <td>${w.department}</td>
          <td>${w.name}</td>
          <td>${w.address}</td>
          <td>${w.phone}</td>
          <td>${w.region}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>

<c:if test="${!empty works}">
  Выбрать предприятия, для которого нужно вывести информацию:
  <form action="/workInfo" method="post">
    <select name="work">
      <c:forEach var="w" items="${works}">
        <option value="${w}">${w}</option>
      </c:forEach>
    </select>
    <input type="submit">
  </form>
</c:if>

<c:if test="${!empty infoWorks}">
  <table>
    <thead>
      <tr>
        <th>Work Place</th>
        <th>Work Address</th>
        <th>Work Phome</th>
        <th>Work Reason</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${infoWorks}" var="w">
        <tr>
          <td>${w.workPlace}</td>
          <td>${w.workAddress}</td>
          <td>${w.workPhone}</td>
          <td>${w.reason}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>

</body>
</html>
