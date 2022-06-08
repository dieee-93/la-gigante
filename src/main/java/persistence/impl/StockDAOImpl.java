package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.system.stockmanager.Materia;
import model.system.stockmanager.Stock;
import persistence.MateriaDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class StockDAOImpl implements MateriaDAO {

	@Override
	public List<Materia> findAll() {
		try {
			String sql = "SELECT * FROM STOCK";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			Stock.getInstance().getProductosEnStock().clear();;
			while (resultados.next()) {
				
				this.toStock(resultados);
			}
			
			return Stock.getInstance().getProductosEnStock();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Materia find(Integer id) {
		try {
			String sql = "SELECT * FROM STOCK WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Materia materia = null;
			if (resultados.next()) {
				materia= Stock.getInstance().getMateriaById(id);
			}

			return materia;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private void toStock(ResultSet stockRegister) throws SQLException {
			Stock.getInstance().addMateriaById(stockRegister.getInt(1), stockRegister.getDouble(2));
	}

	@Override
	public int insert(Materia materia) {
		try {
			String sql = "INSERT INTO STOCK (ID_MATERIAPRIMA, CANTIDAD) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, materia.getId());
			statement.setDouble(i++, materia.getUnidades());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Materia materia) {
		try {
			String sql = "UPDATE STOCK SET ID_MATERIAPRIMA = ?, CANTIDAD = ?, WHERE ID_MATERIAPRIMA = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, materia.getId());
			statement.setDouble(i++, materia.getUnidades());
		
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Materia materia) {
		try {
			String sql = "DELETE FROM STOCK WHERE ID_MATERIAPRIMA = ?";
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
			String sql = "SELECT COUNT(1) AS TOTAL FROM STOCK";
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

	@Override
	public Materia findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}




}
