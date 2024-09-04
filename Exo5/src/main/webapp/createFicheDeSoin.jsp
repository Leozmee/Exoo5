<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer une Fiche de Soins</title>
</head>
<body>
<h1>Créer une Fiche de Soins</h1>
<form action="${pageContext.request.contextPath}/fiches-de-soin" method="post">
    Contenu: <textarea name="contenu"></textarea><br/>
    <input type="submit" value="Créer"/>
</form>
</body>
</html>
