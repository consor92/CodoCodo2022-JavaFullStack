package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.codoacodo.connection.ServerHost;
import ar.com.codoacodo.daos.ProductoDAO;
import ar.com.codoacodo.dto.Producto;

@WebServlet("/api/BuscarController")
public class BuscarController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String clave = req.getParameter("clave");

		ProductoDAO dao = new ProductoDAO(ServerHost.MySQL);
		LinkedList<Producto> listado = dao.buscar(clave);

		req.setAttribute("listado", listado);

		getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
	}
}
