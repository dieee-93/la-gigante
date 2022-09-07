package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.nullobjects.NullCategoria;
import model.system.stockmanager.Categoria;
import persistence.CategoriaDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class CategoriaDAOImpl implements CategoriaDAO {

	public List<Categoria> findAll() {
		try {
			String sql = "SELECT * FROM CATEGORIAS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Categoria> categorias = new LinkedList<Categoria>();

			while (resultados.next()) {
				categorias.add(toCategoria(resultados));
			}

			return categorias;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Categoria find(Integer id) {
		try {
			String sql = "SELECT * FROM CATEGORIAS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Categoria categoria = NullCategoria.build();
			if (resultados.next()) {
				categoria = toCategoria(resultados);
			}

			return categoria;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Categoria toCategoria(ResultSet categoriaRegister) throws SQLException {
		return new Categoria(categoriaRegister.getInt(1), categoriaRegister.getString(2), categoriaRegister.getInt(3));
	}

	@Override
	public int insert(Categoria cat) {
		try {
			String sql = "INSERT INTO CATEGORIAS (NOMBRE, CATEGORIA_PADRE) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, cat.getNombre());
			statement.setInt(i++, cat.getIdCategoriaPadre());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		} 

	}

	

	@Override
	public int update(Categoria cat) {
		try {
			String sql = "UPDATE CATEGORIAS SET NOMBRE = ?, CATEGORIA_PADRE = ?, WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, cat.getNombre());
			statement.setInt(i++, cat.getIdCategoriaPadre());
			statement.setInt(i++, cat.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Categoria cat) {
		try {
			String sql = "DELETE FROM CATEGORIAS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, cat.getId());

			return statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {

			String sql = "SELECT COUNT(1) AS TOTAL FROM CATEGORIAS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();

			return resultados.getInt("TOTAL");

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}