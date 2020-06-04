<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ page import="entity.Escritor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Escritor</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<%
		Escritor escritor = (Escritor)request.getAttribute("escritor");
	%>
	<div style="padding: 30px">
		<h2>Cadastro de Escritor</h2>
		<form action="escritor" method="POST">
			<div class="form-group">
				<div class="row">
					<input name="id" type="hidden" value=
						"<%=(escritor != null ? escritor.getPessoa().getId():"")%>">
					<div class="col-md-6">
						<label>Nome</label>
						<input name="nome" class="form-control" value=
							"<%=(escritor != null ? escritor.getPessoa().getNome():"")%>">
					</div>
					<div class="col-md-2">
						<label>Nascimento</label>
						<input name="dataNascimento" type="date" class="form-control" value=
							"<%=(escritor != null ? escritor.getPessoa().getDataNascimento():"")%>">
					</div>
					<div class="col-md-8">
						<label>Observação</label>
						<textarea name="observacao" class="form-control" 
							value><%=(escritor != null ? escritor.getPessoa().getObservacao():"")%></textarea>
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