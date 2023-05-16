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
	
	public void insertarProducto(Producto producto){
		try {
			Conector conector = new Conector();
			conector.conectar();

			PreparedStatement pSt = conector.getCon().prepareStatement("INSERT INTO productos (codigo, nombre, cantidad, precio, caducidad,) Values (?,?,?,?,?)");
			pSt.setString(1, producto.getCodigo());;
			pSt.setString(2, producto.getNombre());
			pSt.setInt(3, producto.getCantidad());
			pSt.setDouble(4, producto.getPrecio());
			pSt.setDate(5, (java.sql.Date) new Date(producto.getCaducidad().getTime()));			
			pSt.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
