<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.EscritorDao" %>
<%@ page import="entity.Escritor" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listagem Escritores</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div style="padding: 30px">
		<h2>Listagem Escritores</h2>
		<br>
		<button class="btn btn-warning" style="color: white" onclick="voltar()">Voltar</button>
		<a class="btn btn-primary" href="escritor?acao=inserir">Cadastrar</a>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Nascimento</th>
					<th>Observação</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Escritor> escritores = (ArrayList<Escritor>)request.getAttribute("escritores");
					for(Escritor escritor : escritores){ 
				%>
				<tr>
					<td style="display: none"></td>
					<td><%= escritor.getPessoa().getNome() %></td>
					<td><%= escritor.getPessoa().getDataNascimento() %></td>
					<td><%= escritor.getPessoa().getObservacao() %></td>
					<td>
						<a class="btn btn-warning" href="escritor?acao=atualizar&id=<%=escritor.getId()%>">
							Editar
						</a>
						<a class="btn btn-danger" href="escritor?acao=excluir&id=<%=escritor.getId()%>">
							Excluir
						</a>
					</td>
				</tr>
				
				<% } %>
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script>
		function voltar(){
			window.location.href="/exemploJSP/"; 
		}
	</script>
</body>
</html>