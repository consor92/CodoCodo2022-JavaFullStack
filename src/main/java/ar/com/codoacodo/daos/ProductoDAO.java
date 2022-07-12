package ar.com.codoacodo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import ar.com.codoacodo.connection.FactoryConnection;
import ar.com.codoacodo.connection.ServerHost;
import ar.com.codoacodo.dto.Producto;

public class ProductoDAO {

	private ServerHost server;

	/**
	 * 
	 * @param serverP
	 */
	public ProductoDAO(ServerHost serverP) {
		this.server = serverP;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Producto obtenerPorId(Long id) {
		String sql = "SELECT * FROM producto WHERE id=?";

		Connection con = FactoryConnection.getInstace().getConnection(server);

		Producto prodFromDb = null;

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				prodFromDb = prepararObjeto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodFromDb;
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<Producto> listarProductos() {
		String sql = "SELECT * FROM PRODUCTO ";

		Connection con = FactoryConnection.getInstace().getConnection(server);

		LinkedList<Producto> list = new LinkedList<Producto>();

		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {//
				list.add(prepararObjeto(rs));
			}

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param nombre
	 * @param precio
	 * @param imagen
	 * @param codigo
	 */
	public void crearProducto(String nombre, Float precio, String imagen, String codigo) {

		Connection con = null;
		do {
			con = FactoryConnection.getInstace().getConnection(server);
		} while (con == null);

		String sql = "INSERT INTO PRODUCTO (nombre, precio,fecha_creacion,"
				+ "imagen,codigo) VALUES( ? , ? , CURRENT_DATE , ? , ?)";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nombre);
			st.setFloat(2, precio);
			st.setString(3, imagen);
			st.setString(4, codigo);

			st.execute();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param codigo
	 * @param nombre
	 * @param precio
	 */
	public void actualizarProducto(String codigo, String nombre, String precio) {
		Connection con = null;
		do {
			con = FactoryConnection.getInstace().getConnection(server);
		} while (con == null);

		String sql = "UPDATE producto SET nombre = ?, precio = ? WHERE codigo = ?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nombre);
			st.setFloat(2, Float.valueOf(precio));
			st.setString(3, codigo);

			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param clave
	 * @return
	 */
	public LinkedList<Producto> buscar(String clave) {
		String sql = "SELECT * FROM producto WHERE nombre LIKE '%"+ clave + "%' ";

		Connection con = FactoryConnection.getInstace().getConnection(server);

		LinkedList<Producto> listado = new LinkedList<Producto>();

		try {
			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				listado.add(prepararObjeto(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listado;
	}

	/**
	 * 
	 * @param id
	 */
	public void eliminar(String id) {
		Connection con = null;
		do {
			con = FactoryConnection.getInstace().getConnection(server);
		} while (con == null);

		String sql = "DELETE FROM producto WHERE id=?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, Long.parseLong(id));

			st.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Producto prepararObjeto(ResultSet rs) throws SQLException {
		Long idProducto = rs.getLong("id");
		String nombre = rs.getString("nombre");
		Float precio = rs.getFloat("precio");
		Date fecha = rs.getDate("fecha_creacion");
		String imagen = rs.getString("imagen");
		String codigo = rs.getString("codigo");

		Producto prodFromDb = new Producto(idProducto, nombre, precio, fecha, imagen, codigo);

		return prodFromDb;
	}
}
