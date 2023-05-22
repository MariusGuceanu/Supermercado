package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.Conector;
import ModeloDAO.ModeloProducto;
import ModeloDAO.ModeloSeccion;
import ModeloDTO.Producto;
import ModeloDTO.Seccion;

/**
 * Servlet implementation class InsertarProducto
 */
@WebServlet("/InsertarProducto")
public class InsertarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertarProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("secciones", ModeloSeccion.getSecciones());
		request.getRequestDispatcher("InsertarForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		ModeloProducto mp = new ModeloProducto();

		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		java.util.Date caducidad = null;

		try {
			caducidad = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("caducidad"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int id_seccion = Integer.parseInt(request.getParameter("seccion"));
		Producto pr = new Producto();

		pr.setCodigo(codigo);
		pr.setNombre(nombre);
		pr.setCantidad(cantidad);
		pr.setPrecio(precio);
		pr.setCaducidad(caducidad);
		Seccion seccion = ModeloSeccion.getSeccion(Integer.parseInt(request.getParameter("seccion")));
		pr.setSeccion(seccion);

		if (pr.getPrecio() < 0 || pr.getCantidad() < 0) {
			request.setAttribute("mensaje", "El precio o cantidad incorrecta");
			doGet(request, response);
		} else if (pr.getSeccion().getId() == 0) {
			request.setAttribute("mensaje", "Id seccion incorrecto");
			doGet(request, response);
//		}else if (pr.getCaducidad().before(new Date())) {
//				request.setAttribute("mensaje", "La fecha no puede ser anterior a la actual");
//				doGet(request, response);
		} else {
			mp.insertarProducto(pr);
		}
	}
}
