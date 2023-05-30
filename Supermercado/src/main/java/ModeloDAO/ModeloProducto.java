package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModeloDAO.*;
import ModeloDTO.*;

public class ModeloProducto {

	public static ArrayList<Producto> getProductos() throws SQLException {
		ArrayList<Producto> productos = new ArrayList<>();
		Conector conector = new Conector();
		ModeloSeccion ms = new ModeloSeccion();
		ModeloSupermercado msp = new ModeloSupermercado();
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
			String sql = "UPDATE productos SET codigo = ?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion=? WHERE id = ? ";

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

		} catch (SQLException e) {
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
			
			pr.setId(resultado.getInt("id"));
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
	
	public int maxId() {
		PreparedStatement pSt;
		Conector conector = new Conector();


		try {
			conector.conectar();
			pSt = conector.getCon().prepareStatement("SELECT MAX(id) FROM productos");

			
			ResultSet result = pSt.executeQuery();
			result.next();
			int maxId= result.getInt(1);
			conector.cerrar();
			return maxId;
		} catch (SQLException e) {

			e.printStackTrace();
			return 0;
		}
	}
	
	public static void eliminarProducto(String id) {
		Conector conector = new Conector();
		String sentencia = "update productos set cantidad=cantidad-1 where id=?";
		String sentencia2 ="delete from productos_supermercados where id_producto=?";
		String sentencia3 ="delete from productos where id=?";

		conector.conectar();

		if (getCantidad(id) > 0) {
			try {
				PreparedStatement pst = conector.getCon().prepareStatement(sentencia);
				pst.setString(1, id);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (getProductosSupermercados(id)) {
			try {
				PreparedStatement pst = conector.getCon()
						.prepareStatement(sentencia2);
				pst.setString(1, id);
				pst.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement pt = conector.getCon().prepareStatement(sentencia3);
				pt.setString(1, id);
				pt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		conector.cerrar();
	}
	
	protected static int getCantidad(String id) {
		Conector conector = new Conector();
		int cantidad = 0;
		conector.conectar();

		try {
			PreparedStatement pst = conector.getCon().prepareStatement("select cantidad from productos where id = ?");
			pst.setString(1, id);
			ResultSet resultado = pst.executeQuery();
			resultado.next();
			cantidad = resultado.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return cantidad;
	}
	private static boolean getProductosSupermercados(String id) {
		
		Conector conector = new Conector();
		conector.conectar();

		boolean existe = false;
		String sentencia = "select * from productos_supermercados where id = ?";

		try {
			PreparedStatement pst = conector.getCon().prepareStatement(sentencia);
			pst.setString(1, id);
			ResultSet r = pst.executeQuery();
			if (r.next()) {
				existe = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		conector.cerrar();
		return existe;
	}
	
	public void borrarMultiple(String[] codigos) {
		Conector conector = new Conector();
		conector.conectar();
		try {
			PreparedStatement pst = conector.getCon()
					.prepareStatement("delete from productos where codigo=?");
			for(String codigo:codigos) {
				pst.setString(1, codigo);
				pst.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		conector.cerrar();
	}
	
	public static void borrarCompleto(int id_producto) {
		Conector conector = new Conector();
		conector.conectar();
		String sql = "delete from productos where id=?";
		
		try {
			PreparedStatement pSt= conector.getCon().prepareStatement(sql);
			pSt .setInt(1, id_producto);
			pSt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
