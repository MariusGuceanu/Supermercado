package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModeloDTO.Seccion;

public class ModeloSeccion {

	public static Seccion getSeccion(int id) {

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

	public static ArrayList<Seccion> getSecciones() {
		ArrayList<Seccion> secciones = new ArrayList<Seccion>();

		String sql = "SELECT * FROM secciones";
		Conector conector = new Conector();
		conector.conectar();
		PreparedStatement pSt = null;
		try {
			pSt = conector.getCon().prepareStatement(sql);
			ResultSet result = pSt.executeQuery();

			while (result.next()) {
				Seccion sc = new Seccion();

				sc.setId(result.getInt("id"));
				sc.setNombre(result.getString("nombre"));

				secciones.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pSt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conector.cerrar();
		return secciones;
	}

}
