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
	<form action="">
		<table>
			<tr>
				<td><input type="text" name="idcon" class="caixa3" readonly/></td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="Caixa1" /></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Caixa2" /></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" /></td>
			</tr>
		</table>
		<input type="button" class="Botao1" value="Salvar" onclick="validar()" />
	</form>
	<script type="script/validador.js"></script>
</body>
</html>