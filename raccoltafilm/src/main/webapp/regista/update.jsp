<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="it.prova.raccoltafilm.model.Regista"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Update</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

	<div
		class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
		role="alert">
		${errorMessage}
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>

	<div class='card'>
		<div class='card-header'>
			<h5>Update regista</h5>
		</div>

		<div class='card-body'>

			<form method="post"
				action="ExecuteUpdateRegistaServlet?idRegista=${update_regista_attr.id}">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Nome</label> <input type="text" name="nome" id="nome"
							class="form-control" placeholder="Inserire il nome"
							value="${update_regista_attr.nome}">
					</div>

					<div class="form-group col-md-6">
						<label>Cognome</label> <input type="text" name="cognome"
							id="cognome" class="form-control"
							placeholder="Inserire il cognome"
							value="${update_regista_attr.cognome}">
					</div>
				</div>

				<div class="form-group col-md-6">
					<label>Nickname</label> <input type="text" name="nickName"
						id="nickName" class="form-control"
						placeholder="Inserire il nickname"
						value="${update_regista_attr.nickName}">
				</div>
		

		<div class="form-row">
			<div class="form-group col-md-6">
				<label>Data di Nascita</label> <input class="form-control"
					id="dataDiNascita" type="date" placeholder="dd/MM/yy"
					title="formato : gg/mm/aaaa" name="dataDiNascita"
					value="${update_regista_attr.dataDiNascita}">
			</div>
		</div>


		<button type="submit" name="submit" value="submit" id="submit"
			class="btn btn-primary">Conferma</button>

	</form>
		<!-- end card-body -->
	</div>
	
	</div>

	<!-- end container --> </main>
	<jsp:include page="../footer.jsp" />

</body>
</html>