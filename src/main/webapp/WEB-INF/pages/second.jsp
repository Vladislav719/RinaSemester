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

</body>
</html>
