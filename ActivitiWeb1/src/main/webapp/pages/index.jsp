<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Hello World Activiti</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="/ActivitiWeb1/avviaIncarico" method="get">
		<table>
			<tr>
				<td><b>Crea Incarico</b></td>
				<td><input type="text" name="codice" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Avvia"></td>
			</tr>
		</table>
	</form>
	<form action="/ActivitiWeb1/accettaIncarico" method="get">
		<table>
			<tr>
				<td><b>Accetta Incarico</b></td>
				<td><input type="text" name="codice" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Accetta"></td>
			</tr>		
		</table>
	</form>
	<form action="/ActivitiWeb1/visualizzaIncarico" method="get">
		<table>
			<tr>
				<td><b>Vedi Incarico</b></td>
				<td><input type="text" name="codice" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Vedi"></td>
			</tr>		
		</table>
	</form>
	<form action="/ActivitiWeb1/incarichiAttivi" method="get">
		<input type="submit" value="Incarichi Attivi">
	</form>
</body>
</html>
