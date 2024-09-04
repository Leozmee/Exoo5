<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<h1>Accueil</h1>
<ul>
    <li><a href="${pageContext.request.contextPath}/patients/new">Créer un Patient</a></li>
    <li><a href="${pageContext.request.contextPath}/patients">Liste des Patients</a></li>
    <li><a href="${pageContext.request.contextPath}/login">Connexion</a></li>
</ul>
</body>
</html>
