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
import ModeloDTO.Producto;

/**
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto mp = new ModeloProducto();
		ArrayList<Producto> productos = null;
		try {
			 productos = mp.getProductos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String busqueda = request.getParameter("codigo");
		
		ModeloProducto mp = new ModeloProducto();
		ArrayList<Producto> productos = null;
		try {
			 productos = mp.getProductos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Producto> productosB = new ArrayList<Producto>();
		for (Producto producto : productos) {
			if (producto.getCodigo().contains(busqueda)) {
				productosB.add(producto);
				
			}
		}
		
		request.setAttribute("productos", productosB);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

	}
}
