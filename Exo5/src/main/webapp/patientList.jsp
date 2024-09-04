<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Liste des Patients</title>
</head>
<body>
<h1>Liste des Patients</h1>
<form action="${pageContext.request.contextPath}/patients" method="get">
  Recherche: <input type="text" name="search"/><input type="submit" value="Rechercher"/>
</form>
<ul>
  <c:forEach var="patient" items="${patients}">
    <li><a href="${pageContext.request.contextPath}/patients/${patient.id}">${patient.nom} ${patient.prenom}</a></li>
  </c:forEach>
</ul>
</body>
</html>
