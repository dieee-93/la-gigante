package model.nullobjects;


import model.system.tienda.Producto;

public class NullProducto extends Producto {

	public static Producto build() {
		return new NullProducto();
	}

	public NullProducto() {
		 super(0, "", "", 0, 0.0, null, null);
	}

	@Override
	public Boolean isNull() {
		return true;
	}

}
