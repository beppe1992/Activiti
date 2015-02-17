<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Stato Incarico Activiti</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="/ActivitiWeb1/index" method="get">
		<input type="submit" value="Indietro">
	</form>
	
	<br>
	<img src="/ActivitiWeb1/activiti/process-instance/diagram?id=${processo}" alt="immagine processo" />

</body>
</html>
