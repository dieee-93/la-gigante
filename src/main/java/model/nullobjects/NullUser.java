package model.nullobjects;


import model.Usuario;

public class NullUser extends Usuario {

	public static Usuario build() {
		return new NullUser();
	}

	public NullUser() {
		super(0, "", "", null, "", false);
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
