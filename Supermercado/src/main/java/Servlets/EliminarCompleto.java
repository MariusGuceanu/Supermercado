package Servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ModeloDAO.ModeloProducto;

/**
 * Servlet implementation class EliminarCompleto
 */
@WebServlet("/EliminarCompleto")
public class EliminarCompleto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCompleto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String [] ids_productosStr = request.getParameterValues("idsproductos");
		int[] id_productos =  Arrays.stream(ids_productosStr).mapToInt(Integer::parseInt).toArray();
		
		for (int id_producto : id_productos) {
			ModeloProducto.borrarCompleto((id_producto));

		}
		response.sendRedirect("VerProductos");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
