package model.nullobjects;


import model.system.materiaprima.Materia;
import utils.JavaObjectToJSON.POJO.MateriaPOJO;

public class NullMateria extends Materia {

	public static Materia build() {
		return new NullMateria();
	}

	public NullMateria() {
		 super(0, "", 0, "", 0.0, 0.0);
	}

	@Override
	public Boolean isNull() {
		return true;
	}

	@Override
	public MateriaPOJO toPOJO() {
	
		return null;
	}

	@Override
	public Double getPrecioMinimo() {

		return null;
	}

}
