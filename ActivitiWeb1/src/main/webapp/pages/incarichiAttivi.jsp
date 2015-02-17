<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Incarichi Attivi</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="/ActivitiWeb1/index" method="get">
		<input type="submit" value="Indietro">
	</form>

	<h3>totale incarichi: ${totale}</h3>

	<br />

	<table width="40%" border="1" align="center">
		<tr bgcolor="#949494">
			<th>Codice incarico</th>
			<th>id processo</th>
		</tr>
		<c:forEach var="incarico" items="${listaIncarichi}" >
     		 <tr><td><c:out value="${incarico.codiceIncarico}"/></td><td><c:out value="${incarico.processoId}"/></td></tr>
   		</c:forEach>
	</table>

</body>
</html>
