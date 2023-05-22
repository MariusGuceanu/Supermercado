package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ModeloProducto;
import ModeloDAO.ModeloSeccion;
import ModeloDTO.Producto;
import ModeloDTO.Seccion;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("producto", ModeloProducto.getProducto(request.getParameter("id")));
		request.setAttribute("secciones", ModeloSeccion.getSecciones());
		request.getRequestDispatcher("ModificarForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto mp = new ModeloProducto();
		int id = Integer.parseInt(request.getParameter("id"));
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

		pr.setId(id);
		pr.setCodigo(codigo);
		pr.setNombre(nombre);
		pr.setCantidad(cantidad);
		pr.setPrecio(precio);
		pr.setCaducidad(caducidad);
		Seccion seccion = ModeloSeccion.getSeccion(Integer.parseInt(request.getParameter("seccion")));
		pr.setSeccion(seccion);
		
		mp.modificarProducto(pr);

		response.sendRedirect("VerProductos");	
		}

}
