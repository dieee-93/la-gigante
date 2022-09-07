package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.nullobjects.NullMateria;
import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import model.system.materiaprima.MateriaContable;
import model.system.materiaprima.MateriaElaborada;
import persistence.MateriaDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class MateriaDAOImpl implements MateriaDAO {

	public List<Materia> findAll() {
		try {
			String sql = "SELECT * FROM MATERIAS_PRIMAS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Materia> materiasPrimas = new LinkedList<Materia>();

			while (resultados.next()) {
				materiasPrimas.add(toMateria(resultados));
			}

			return materiasPrimas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Materia find(Integer id) {
		try {
			String sql = "SELECT * FROM MATERIAS_PRIMAS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Materia materiaPrima = NullMateria.build();
			if (resultados.next()) {
				materiaPrima = toMateria(resultados);
			}

			return materiaPrima;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Materia findByName(String name) {
		try {
			String sql = "SELECT * FROM MATERIAS_PRIMAS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);

			ResultSet resultados = statement.executeQuery();

			Materia materiaPrima = NullMateria.build();
			if (resultados.next()) {
				materiaPrima = toMateria(resultados);
			}

			return materiaPrima;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Materia toMateria(ResultSet materiaRegister) throws SQLException {
		Materia materia = null;

		switch (materiaRegister.getString(4)) {
		case ("conmesurable"):
			materia = new MateriaConmesurable(materiaRegister.getInt(1), materiaRegister.getString(2),
					materiaRegister.getString(3), materiaRegister.getString(4), materiaRegister.getDouble(5),
					materiaRegister.getDouble(6), materiaRegister.getString(7));
			break;
		case ("contable"):
			materia = new MateriaContable(materiaRegister.getInt(1), materiaRegister.getString(2),
					materiaRegister.getString(3), materiaRegister.getString(4), materiaRegister.getDouble(5),
					materiaRegister.getDouble(6));
			break;
		case ("elaborada"):
			if (materiaRegister.getString(7) == null) {
				materia = new MateriaElaborada(materiaRegister.getInt(1), materiaRegister.getString(2),
						materiaRegister.getString(3), materiaRegister.getString(4), materiaRegister.getDouble(5),
						materiaRegister.getDouble(6), this.lectorDeRecetas(materiaRegister.getString(8)));
			} else {
				materia = new MateriaElaborada(materiaRegister.getInt(1), materiaRegister.getString(2),
						materiaRegister.getString(3), materiaRegister.getString(4), materiaRegister.getDouble(5),
						materiaRegister.getDouble(6), materiaRegister.getString(7),
						this.lectorDeRecetas(materiaRegister.getString(8)));
			}
			break;
			default:
				//TODO
				break;
		}

		return materia;

	}

	// RECIBE UN STRING FORMATO:

	// IDMATERIA-CANTIDAD-UNIDADDEMEDIDA(SOLO CONMESURABLES Y ELABORADAS
	// CONMESURABLES)/IDMATERIA2-CANTIDAD2-UNIDADDEMEDIDA2/IDMATERIA3-CANTIDAD3-UNIDADDEMEDIDA3/ETC
	// "-" SEPARAN PARAMETROS "/" SEPARAN MATERIAS_PRIMAS

	public List<Materia> lectorDeRecetas(String receta) {

		List<Materia> res = new LinkedList<Materia>();

		String[] listaDeIngredientes = receta.split("/");

		for (int i = 0; i < listaDeIngredientes.length; i++) {
			String[] ingrediente = listaDeIngredientes[i].split("-");
			Materia tmp_materia = this.find(Integer.parseInt(ingrediente[0]));
			Materia tmp_materia2;
			switch (tmp_materia.getTipo()) {

			case ("conmesurable"):
				tmp_materia2 = new MateriaConmesurable(tmp_materia.getId(), tmp_materia.getNombre(),
						tmp_materia.getCategoria(), tmp_materia.getTipo(), tmp_materia.getCosto(),
						Double.parseDouble(ingrediente[1]), ingrediente[2]);
				res.add(tmp_materia2);
				break;
			case ("contable"):
				tmp_materia2 = new MateriaContable(tmp_materia.getId(), tmp_materia.getNombre(),
						tmp_materia.getCategoria(), tmp_materia.getTipo(), tmp_materia.getCosto(),
						Double.parseDouble(ingrediente[1]));
				res.add(tmp_materia2);
				break;
			case ("elaborada"):
				// REVISA SI LA MATERIA ELABORADA ES CONMESURABLE O CONTABLE
				if (tmp_materia.getUnidadDeMedida() == null) {
					tmp_materia2 = new MateriaElaborada(tmp_materia.getId(), tmp_materia.getNombre(),
							tmp_materia.getCategoria(), tmp_materia.getTipo(), tmp_materia.getCosto(),
							tmp_materia.getCantidad(), this.lectorDeRecetas(ingrediente[1]));

				} else {
					tmp_materia2 = new MateriaElaborada(tmp_materia.getId(), tmp_materia.getNombre(),
							tmp_materia.getCategoria(), tmp_materia.getTipo(), tmp_materia.getCosto(),
							tmp_materia.getCantidad(), ingrediente[1], this.lectorDeRecetas(ingrediente[2]));
				}
				res.add(tmp_materia2);
				break;

			}

		}

		return res;
	}

	@Override
	public int insert(Materia materia) {
		try {
			Connection conn = ConnectionProvider.getConnection();
			int rows = 0;
			if (materia.getTipo().equals("conmesurable")) {
				String sql = "INSERT INTO MATERIAS_PRIMAS (NOMBRE, CATEGORIA, TIPO_MATERIA, COSTO, CANTIDAD, UNIDAD_DE_MEDIDA) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getUnidadDeMedida());

				rows = statement.executeUpdate();
			}

			if (materia.getTipo().equals("contable")) {
				String sql = "INSERT INTO MATERIAS_PRIMAS (NOMBRE, CATEGORIA, TIPO_MATERIA, COSTO, CANTIDAD) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());

				rows = statement.executeUpdate();
			}

			if (materia.getTipo().equals("elaborada") && materia.getUnidadDeMedida() == null) {
				String sql = "INSERT INTO MATERIAS_PRIMAS (NOMBRE, CATEGORIA, TIPO_MATERIA, COSTO, CANTIDAD, RECETA) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getRecetaTextoPlano());

				rows = statement.executeUpdate();
			} else if(materia.getTipo().equals("elaborada") && materia.getUnidadDeMedida() != null) {
				String sql = "INSERT INTO MATERIAS_PRIMAS (NOMBRE, CATEGORIA, TIPO_MATERIA, COSTO, CANTIDAD, UNIDAD_DE_MEDIDA, RECETA) VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getUnidadDeMedida());
				statement.setString(i++, materia.getRecetaTextoPlano());
				
				rows = statement.executeUpdate();
				
			}
			

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Materia materia) {
		try {

			Connection conn = ConnectionProvider.getConnection();
			int rows = 0;

			if (materia.getTipo().equals("conmesurable")) {
				String sql = "UPDATE MATERIAS_PRIMAS SET NOMBRE = ?, CATEGORIA = ?, TIPO_MATERIA = ?, COSTO = ?, CANTIDAD = ?, UNIDAD_DE_MEDIDA = ? WHERE ID = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getUnidadDeMedida());

				statement.setInt(i++, materia.getId());

				rows = statement.executeUpdate();
			}

			if (materia.getTipo().equals("contable")) {
				String sql = "UPDATE MATERIAS_PRIMAS SET NOMBRE = ?, CATEGORIA = ?, TIPO_MATERIA = ?, COSTO = ?, CANTIDAD = ? WHERE ID = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());

				statement.setInt(i++, materia.getId());

				rows = statement.executeUpdate();
			}

			if (materia.getTipo().equals("elaborada") && materia.getUnidadDeMedida() == null) {
				String sql = "UPDATE MATERIAS_PRIMAS SET NOMBRE = ?, CATEGORIA = ?, TIPO_MATERIA = ?, COSTO = ?, CANTIDAD = ? WHERE ID = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getRecetaTextoPlano());

				statement.setInt(i++, materia.getId());

				rows = statement.executeUpdate();
			} else if (materia.getTipo().equals("elaborada") && materia.getUnidadDeMedida() != null) {
				String sql = "UPDATE MATERIAS_PRIMAS SET NOMBRE = ?, CATEGORIA = ?, TIPO_MATERIA = ?, COSTO = ?, CANTIDAD = ? WHERE ID = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				int i = 1;
				statement.setString(i++, materia.getNombre());
				statement.setString(i++, materia.getCategoria());
				statement.setString(i++, materia.getTipo());
				statement.setDouble(i++, materia.getCosto());
				statement.setDouble(i++, materia.getCantidad());
				statement.setString(i++, materia.getUnidadDeMedida());;
				statement.setString(i++, materia.getRecetaTextoPlano());

				statement.setInt(i++, materia.getId());
			}

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Materia materia) {
		try {
			String sql = "DELETE FROM MATERIAS_PRIMAS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, materia.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM MATERIAS_PRIMAS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}