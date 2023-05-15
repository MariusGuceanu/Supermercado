package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModeloDTO.Seccion;

public class ModeloSeccion {

	public Seccion getSeccion(int id) {

		String sql = "select * from secciones where id=?";
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement pSt;
		Seccion seccion = new Seccion();
		try {
			pSt = conector.getCon().prepareStatement(sql);
			pSt.setInt(1, id);
			ResultSet resultado = pSt.executeQuery();
			resultado.next();
			seccion.setId(resultado.getInt("id"));
			seccion.setNombre(resultado.getString("nombre"));
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conector.cerrar();
		return seccion;

	}
}
