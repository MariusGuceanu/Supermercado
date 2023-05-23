package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.ModeloProducto;
import ModeloDAO.ModeloSeccion;
import ModeloDTO.Producto;

/**
 * Servlet implementation class Filtrar
 */
@WebServlet("/Filtrar")
public class Filtrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filtrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto mp = new ModeloProducto();
		ArrayList<Producto> productos = null;
		try {
			productos = mp.getProductos();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ArrayList<Producto> productosF = new ArrayList<Producto>();
		
		for (Producto producto : productos) {
			if(producto.getPrecio()<Double.parseDouble(request.getParameter("max"))&&producto.getPrecio()>Double.parseDouble(request.getParameter("min"))) {
				productosF.add(producto); 
			}
		}
		
		request.setAttribute("productos", productosF);	
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
	}

}
