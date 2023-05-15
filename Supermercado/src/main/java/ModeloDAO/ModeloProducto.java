package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModeloDAO.*;
import ModeloDTO.*;

public class ModeloProducto {

	public ArrayList<Producto> getProductos() throws SQLException {
		ArrayList<Producto> productos = new ArrayList<>();
		Conector conector = new Conector();
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

			productos.add(pr);
		}
		pSt.close();
		conector.cerrar();
		return productos;

	}
}
