<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<jsp:include page="../header.jsp" />
<title>Elimina</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

	<div class="card">
		<h5 class="card-header">Elimina</h5>
		<div class="card-body">
			<form method="post"
				action="ExecuteDeleteRegistaServlet?idRegista=${delete_regista_attr.id}">
				<h5 class="card-title">Stai eliminando definitivamente il
					regista con ID: ${delete_regista_attr.id}</h5>
				<p class="card-text">Vuoi procedere nell'eliminazione?</p>
				<button type="submit" name="submit" id="submit"
					class="btn btn-danger">Delete</button>
			</form>
		</div>
	</div>

	</main>

	<jsp:include page="../footer.jsp" />
</body>
</html>