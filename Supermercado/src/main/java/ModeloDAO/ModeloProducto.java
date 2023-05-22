package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ModeloDAO.*;
import ModeloDTO.*;

public class ModeloProducto {

	public ArrayList<Producto> getProductos() throws SQLException {
		ArrayList<Producto> productos = new ArrayList<>();
		Conector conector = new Conector();
		ModeloSeccion ms = new ModeloSeccion();
		conector.conectar();

		PreparedStatement pSt = conector.getCon().prepareStatement("SELECT * FROM productos");
		ResultSet resultado = pSt.executeQuery();
		while (resultado.next()) {
			Producto pr = new Producto();

			pr.setId(resultado.getInt("id"));
			pr.setCodigo(resultado.getString("codigo"));
			pr.setNombre(resultado.getString("nombre"));
			pr.setCantidad(resultado.getInt("cantidad"));
			pr.setPrecio(resultado.getDouble("precio"));
			pr.setCaducidad(resultado.getDate("caducidad"));
			pr.setSeccion(ms.getSeccion(resultado.getInt("id_seccion")));
			productos.add(pr);
		}
		pSt.close();
		conector.cerrar();
		return productos;

	}

	public void insertarProducto(Producto producto) {
		try {
			Conector conector = new Conector();
			conector.conectar();

			PreparedStatement pSt = conector.getCon().prepareStatement(
					"INSERT INTO productos (codigo, nombre, cantidad, precio, caducidad, id_seccion) Values (?,?,?,?,?,?)");
			pSt.setString(1, producto.getCodigo());
			pSt.setString(2, producto.getNombre());
			pSt.setInt(3, producto.getCantidad());
			pSt.setDouble(4, producto.getPrecio());
			pSt.setDate(5, new java.sql.Date(producto.getCaducidad().getTime()));
			pSt.setInt(6, producto.getSeccion().getId());

			pSt.execute();

			conector.cerrar();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public void modificarProducto(Producto producto) {
		try {
			Conector conector = new Conector();
			conector.conectar();
			String sql = "UPDATE productos SET codigo = ?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion WHERE id = ? ";
			
			PreparedStatement pSt = conector.getCon().prepareStatement(sql);
			pSt.setString(1, producto.getCodigo());
			pSt.setString(2, producto.getNombre());
			pSt.setInt(3, producto.getCantidad());
			pSt.setDouble(4, producto.getPrecio());
			pSt.setDate(5, new java.sql.Date(producto.getCaducidad().getTime()));
			pSt.setInt(6, producto.getSeccion().getId());
			pSt.setInt(7, producto.getId());

			pSt.execute();
			
			conector.cerrar();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Producto getProducto(String id) {

		String sql = "select * from productos where id=?";
		Conector conector = new Conector();
		ModeloSeccion ms = new ModeloSeccion();
		conector.conectar();
		PreparedStatement pSt;
		Producto pr = new Producto();
		try {
			pSt = conector.getCon().prepareStatement(sql);
			pSt.setString(1, id);
			ResultSet resultado = pSt.executeQuery();
			resultado.next();
			pr.setCodigo(resultado.getString("codigo"));
			pr.setNombre(resultado.getString("nombre"));
			pr.setCantidad(resultado.getInt("cantidad"));
			pr.setPrecio(resultado.getDouble("precio"));
			pr.setCaducidad(resultado.getDate("caducidad"));
			pr.setSeccion(ms.getSeccion(resultado.getInt("id_seccion")));
			
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		conector.cerrar();
		return pr;

	}
}
