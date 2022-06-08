package model.nullobjects;


import model.system.materiaprima.Materia;

public class NullMateria extends Materia {

	public static Materia build() {
		return new NullMateria();
	}

	public NullMateria() {
		 super(0, "","");
	}

	@Override
	public Boolean isNull() {
		return true;
	}

}
