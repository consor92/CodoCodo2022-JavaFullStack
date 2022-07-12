package main.java.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.connection.FactoryConections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/CreateController")
public class CreateController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nombre");
		String precio = req.getParameter("precio");
		String fechaCreacion = "";
		String imagen = req.getParameter("imagen");
		String codigo = req.getParameter("codigo");
		
		Connection conn = FactoryConections.getConnection();
		
		if(conn != null)
		{
			String sql = "INSERT INTO producto (nombre , precio , fecha_creacion"
					+ " , imagen , codigo) VALUES ( ? , ? , CURDATE() , ? , ? )";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, nombre);
				ps.setString(2, precio);
				ps.setString(3, imagen);
				ps.setString(4, codigo);
				
				ps.executeUpdate();
				
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		super.doPost(req, resp);
	}	
	
	
}
