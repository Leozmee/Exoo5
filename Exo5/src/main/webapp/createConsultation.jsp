<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer une Consultation</title>
</head>
<body>
<h1>Créer une Consultation</h1>
<form action="${pageContext.request.contextPath}/consultations" method="post">
    Date: <input type="date" name="dateConsultation"/><br/>
    Médecin: <input type="text" name="nomMedecin"/><br/>
    Prescription: <textarea name="prescription"></textarea><br/>
    <input type="submit" value="Créer"/>
</form>
</body>
</html>
