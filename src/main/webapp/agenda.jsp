<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="agenda.model.JavaBeans" %>
    <%@ page import="java.util.List" %>
    <%
    	List<JavaBeans> lista = (ArrayList<JavaBeans>)
    	request.getAttribute("contatos");
    %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	<table id="tabela">
		<thead>
			<tr>
				<td>id</td>
				<td>Nome</td>
				<td>Fone</td>
				<td>Email</td>
				<td>Opções</td>
			</tr>
		</thead>
		<tbody>
			<% for(int i =0; i< lista.size(); i++) {%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td>a<a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="Botao1">Editar</a></td>
				</tr>
			<%}%>
		</tbody>
	</table>
</body>
</html>