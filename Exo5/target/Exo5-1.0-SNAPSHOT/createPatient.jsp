<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Créer un Patient</title>
</head>
<body>
<h1>Créer un Patient</h1>
<form action="${pageContext.request.contextPath}/patients" method="post">
  Nom: <input type="text" name="nom"/><br/>
  Prénom: <input type="text" name="prenom"/><br/>
  Date de Naissance: <input type="date" name="dateNaissance"/><br/>
  Photo: <input type="file" name="photo"/><br/>
  <input type="submit" value="Créer"/>
</form>
</body>
</html>
