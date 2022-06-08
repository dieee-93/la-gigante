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
		
		switch(materiaRegister.getString(4)) {
		case("contable"):
			materia = new MateriaContable(materiaRegister.getInt(1), materiaRegister.getString(2), materiaRegister.getString(3), materiaRegister.getString(4));
		break;
		case("conmesurable"):
			materia = new MateriaConmesurable(materiaRegister.getInt(1), materiaRegister.getString(2), materiaRegister.getString(3), materiaRegister.getString(4));
		
		}
		

		return materia;
			
	}

	@Override
	public int insert(Materia materia) {
		try {
			String sql = "INSERT INTO MATERIAS_PRIMAS (NOMBRE, COSTO_POR_KILO, CANTIDAD, UNIDAD_DE_MEDIDA, CATEGORIA) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, materia.getNombre());
			statement.setDouble(i++, materia.getCostoEnKg());
			statement.setDouble(i++, materia.getCantidad());
			statement.setString(i++, materia.getUnidadDeMedida());
			statement.setString(i++, materia.getCategoria());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Materia materia) {
		try {
			String sql = "UPDATE MATERIAS_PRIMAS SET NOMBRE = ?, COSTO_POR_KILO = ?, CANTIDAD = ?, UNIDAD_DE_MEDIDA = ?, CATEGORIA = ?, WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, materia.getNombre());
			statement.setDouble(i++, materia.getCostoEnKg());
			statement.setDouble(i++, materia.getCantidad());
			statement.setString(i++, materia.getUnidadDeMedida());
			statement.setString(i++, materia.getCategoria());

			statement.setInt(i++, materia.getId());
			int rows = statement.executeUpdate();

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