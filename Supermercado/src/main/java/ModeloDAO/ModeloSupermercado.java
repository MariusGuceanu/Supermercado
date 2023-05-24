package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModeloDTO.Seccion;
import ModeloDTO.Supermercado;

public class ModeloSupermercado {

	public static Supermercado getSupermercado(int id) {

		String sql = "select * from supermercados where id=?";
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement pSt;
		Supermercado superm = new Supermercado();
		try {
			pSt = conector.getCon().prepareStatement(sql);
			pSt.setInt(1, id);
			ResultSet resultado = pSt.executeQuery();
			resultado.next();
			superm.setId(resultado.getInt("id"));
			superm.setNombre(resultado.getString("nombre"));
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		conector.cerrar();
		return superm;

	}
	

	public static ArrayList<Supermercado> getSupermercados() {
		ArrayList<Supermercado> supers = new ArrayList<Supermercado>();

		String sql = "SELECT * FROM supermercados";
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement pSt = null;
		try {
			pSt = conector.getCon().prepareStatement(sql);
			ResultSet result = pSt.executeQuery();

			while (result.next()) {
				Supermercado sp = new Supermercado();

				sp.setId(result.getInt("id"));
				sp.setNombre(result.getString("nombre"));

				supers.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		conector.cerrar();
	}
		return supers;
	}
	
	public boolean insertarProductoSupermercado(int id_producto, String[] supermercados) {
		String sentencia="INSERT INTO productos_supermercados (id_producto,id_supermercado) VALUES(?,?)";
		Conector conector = new Conector();
		
		conector.conectar();
		PreparedStatement pSt = null;
		try {
			pSt=conector.getCon().prepareStatement(sentencia);
			pSt.setInt(1,id_producto);
			for (String string : supermercados) {
				pSt.setInt(2,  Integer.parseInt(string));
				
				pSt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}
}
