<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Détails du Patient</title>
</head>
<body>
<h1>Détails du Patient</h1>

<h2>${patient.nom} ${patient.prenom}</h2>
<p>Date de Naissance: ${patient.dateNaissance}</p>
<img src="${patient.photo}" alt="Photo du patient"/>
<h3>Consultations</h3>
<ul>
    <c:forEach var="consultation" items="${patient.consultations}">
        <li><a href="${pageContext.request.contextPath}/consultations/${consultation.id}">${consultation.dateConsultation} avec ${consultation.nomMedecin}</a></li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/consultations/new">Ajouter une Consultation</a>
</body>
</html>
