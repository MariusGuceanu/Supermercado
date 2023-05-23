package ModeloDTO;

import java.util.ArrayList;

public class Supermercado {

	private int id;
	private String nombre;
	private ArrayList productos;
	
	
	public Supermercado() {
		
	}
	public Supermercado(int id, String nombre, ArrayList productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList getProductos() {
		return productos;
	}
	public void setProductos(ArrayList productos) {
		this.productos = productos;
	}
	
	
}
