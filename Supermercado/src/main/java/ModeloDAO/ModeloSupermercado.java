package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
