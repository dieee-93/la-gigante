package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.system.materiaprima.Materia;
import model.system.stockmanager.Stock;
import persistence.StockDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class StockDAOImpl implements StockDAO  {

	@Override
	public List<Materia> findAll() {
		try {
			String sql = "SELECT * FROM STOCK";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			
			Stock.getInstance().getMateriaEnStock().clear();;
			while (resultados.next()) {
				
				this.toStock(resultados);
			}
			
			return Stock.getInstance().getMateriaEnStockList();
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
			if (Stock.getInstance().getMateriaEnStock().containsKey(id)) {
				Stock.getInstance().getMateriaEnStock().remove(id);
				this.toStock(resultados);
				materia = Stock.getInstance().getMateriaById(id);
		
			}

			return materia;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private void toStock(ResultSet stockRegister) throws SQLException {
			
			Stock.getInstance().setMateriaEnStock(stockRegister.getInt(1), stockRegister.getString(2));
	}

	public int insert(Materia mat) {
		try {
			String sql = "INSERT INTO STOCK (ID_MATERIAPRIMA, CANTIDAD) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, mat.getId());
			statement.setDouble(i++, mat.getCantidad());
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
			statement.setDouble(i++, materia.getCantidad());
			
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




}
