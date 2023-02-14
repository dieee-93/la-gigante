package model.nullobjects;

import model.system.Categoria;

public class NullCategoria extends Categoria {

	public static Categoria build() {
		return new NullCategoria();
	}

	public NullCategoria() {
		super(0, "", 0);
	}

	@Override
	public Boolean isNull() {
		return true;
	}

}
