<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form action="update" name="frmContato">
		<table>
			<tr>
				<td><input type="text" name="idcon" class="caixa3" value="<%out.print(request.getAttribute("idcon"));%>" readonly/></td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"/></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone"));%>"/></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>"/></td>
			</tr>
		</table>
		<input type="button" class="Botao1" value="Salvar" onclick="validar()" />
	</form>
	<script type="script/validador.js"></script>
</body>
</html>