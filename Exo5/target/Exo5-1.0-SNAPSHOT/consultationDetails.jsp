<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Détails de la Consultation</title>
</head>
<body>
<h1>Détails de la Consultation</h1>

<p>Date: ${consultation.dateConsultation}</p>
<p>Médecin: ${consultation.nomMedecin}</p>
<p>Prescription: ${consultation.prescription}</p>
<h3>Fiches de Soins</h3>
<ul>
    <c:forEach var="fiche" items="${consultation.fichesDeSoins}">
        <li>${fiche.contenu}</li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/fiches-de-soin/new">Ajouter une Fiche de Soin</a>
</body>
</html>
