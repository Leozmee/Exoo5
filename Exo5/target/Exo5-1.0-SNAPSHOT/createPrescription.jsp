<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer une Prescription</title>
</head>
<body>
<h1>Créer une Prescription</h1>
<form action="${pageContext.request.contextPath}/prescriptions" method="post">
    Contenu: <textarea name="contenu"></textarea><br/>
    <input type="submit" value="Créer"/>
</form>
</body>
</html>
