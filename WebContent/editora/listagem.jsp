<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.EditoraDao" %>
<%@ page import="entity.Editora" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Listagem Editoras</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div style="padding: 30px">
		<h2>Listagem Editoras</h2>
		<br>
		<button class="btn btn-warning" style="color: white" onclick="voltar()">Voltar</button>
		<a class="btn btn-primary" href="editora?acao=inserir">Cadastrar</a>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Nascimento</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
					ArrayList<Editora> editoras = (ArrayList<Editora>)request.getAttribute("editoras");
					for(Editora editora : editoras){
				%>
				<tr>
					<td style="display: none"></td>
					<td><%= editora.getEmpresa().getNome() %></td>
					<td><%= editora.getEmpresa().getDataAbertura() %></td>
					<td>
						<a class="btn btn-warning" href="editora?acao=atualizar&id=<%=editora.getId()%>">
							Editar
						</a>
						<a class="btn btn-danger" href="editora?acao=excluir&id=<%=editora.getId()%>">
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