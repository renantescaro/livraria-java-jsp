<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Editora" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editora</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<%
		Editora editora = (Editora)request.getAttribute("editora");
	%>
	<div style="padding: 30px">
		<h2>Cadastro de Editora</h2>
		<form action="editora" method="POST">
			<div class="form-group">
				<div class="row">
					<input name="id" type="hidden" value=
						"<%=(editora != null ? editora.getEmpresa().getId():"")%>">
					<div class="col-md-6">
						<label>Nome</label>
						<input name="nome" class="form-control" value=
							"<%=(editora != null ? editora.getEmpresa().getNome():"")%>">
					</div>
					<div class="col-md-2">
						<label>Abertura</label>
						<input name="dataAbertura" type="date" class="form-control" value=
							"<%=(editora != null ? editora.getEmpresa().getDataAbertura():"")%>">
					</div>
				</div>
				<br>
				<button type="button" class="btn btn-danger" onclick="">
					Cancelar
				</button>
				<button class="btn btn-primary">Salvar</button>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>