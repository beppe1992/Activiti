<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Hello World Activiti</title>
</head>
<body>
	<h2>${message}</h2>
	<form action="/ActivitiWeb2/avviaIncarico" method="get">
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
	<form action="/ActivitiWeb2/accettaIncarico" method="get">
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
	<form action="/ActivitiWeb2/evadiIncarico" method="get">
		<table>
			<tr>
				<td><b>Evadi Incarico</b></td>
				<td><input type="text" name="codice" /></td>
			</tr>
			<tr>
				<td><b>importo</b></td>
				<td><input type="text" name="importo" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Evadi"></td>
			</tr>		
		</table>
	</form>
	<form action="/ActivitiWeb2/visualizzaIncarico" method="get">
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
	<form action="/ActivitiWeb2/incarichiAttivi" method="get">
		<input type="submit" value="Incarichi Attivi">
	</form>
</body>
</html>
