<%@page import="java.util.List"%>
<%@page import="ar.com.codoacodo.dto.Producto"%>
<html>
<head>

	<%@include file="head.jsp"%>
<script type="text/javascript">
	const alert = bootstrap.Alert.getOrCreateInstance('#myAlert')
	alert.close()
</script>	
	
</head>

<body>
	<main>

		<%@include file="navbar.jsp"%>
		<div class="container">
			<section>
				<h1>Listado</h1>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<strong>Holy guacamole!</strong> You should check in on some of
					those fields below.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						data-bs-target="#my-alert" aria-label="Close"></button>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">NOMBRE</th>
							<th scope="col">PRECIO</th>
							<th scope="col">C&Oacute;DIGO</th>
							<th scope="col">&nbsp;</th>
						</tr>
					</thead>
					<%
					List<Producto> listado = (List<Producto>) request.getAttribute("listado");
					%>
					<tbody>
						<%
						for (Producto unProducto : listado) {
						%>
						<tr>
							<th scope="row"><%=unProducto.getIdProducto()%></th>
							<td><%=unProducto.getNombre()%></td>
							<td><%=unProducto.getPrecio()%></td>
							<td><%=unProducto.getCodigo()%></td>
							<td><a class="btn btn-danger"
								href="<%=request.getContextPath()%>/api/EliminarController?id=<%=unProducto.getIdProducto()%>"
								role="button">Eliminar</a> | <a class="btn btn-secondary"
								href="<%=request.getContextPath()%>/api/EditarController?id=<%=unProducto.getIdProducto()%>"
								role="button">Editar</a></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</section>
			<nav aria-label="listado">
				<ul class="pagination pagination-lg justify-content-center">
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item active" aria-current="page"><a
						class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
				</ul>
			</nav>
		</div>
	</main>
</body>

</html>