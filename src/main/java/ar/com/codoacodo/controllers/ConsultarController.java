package ar.com.codoacodo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.com.codoacodo.connection.ServerHost;
import ar.com.codoacodo.daos.ProductoDAO;
import ar.com.codoacodo.dto.Producto;

@WebServlet("/api/ConsultarController")
public class ConsultarController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		ProductoDAO dao = new ProductoDAO(ServerHost.MySQL);
		Producto prodFromDb = dao.obtenerPorId(Long.parseLong(id));

		req.setAttribute("producto", prodFromDb);

		getServletContext().getRequestDispatcher("/detalle.jsp").forward(req, resp);
	}
}
