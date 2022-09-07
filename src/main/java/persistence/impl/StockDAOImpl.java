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
import persistence.StockDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class StockDAOImpl implements StockDAO {

	@Override
	public List<Materia> findAll() {
		try {
			String sql = "SELECT MATERIAS_PRIMAS.ID, MATERIAS_PRIMAS.NOMBRE, MATERIAS_PRIMAS.CATEGORIA, MATERIAS_PRIMAS.TIPO_MATERIA, MATERIAS_PRIMAS.COSTO, MATERIAS_PRIMAS.CANTIDAD, STOCK.CANTIDAD,  MATERIAS_PRIMAS.UNIDAD_DE_MEDIDA, MATERIAS_PRIMAS.RECETA FROM stock LEFT JOIN MATERIAS_PRIMAS ON MATERIAS_PRIMAS.ID = STOCK.ID_MATERIAPRIMA";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Materia> materiaEnStock = new LinkedList<>();

			while (resultados.next()) {

				materiaEnStock.add(toMateria(resultados));
			}

			return materiaEnStock;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Materia find(Integer id) {
		try {
			String sql = "SELECT MATERIAS_PRIMAS.ID, MATERIAS_PRIMAS.NOMBRE, MATERIAS_PRIMAS.CATEGORIA, MATERIAS_PRIMAS.TIPO_MATERIA, MATERIAS_PRIMAS.COSTO, MATERIAS_PRIMAS.CANTIDAD, STOCK.CANTIDAD,  MATERIAS_PRIMAS.UNIDAD_DE_MEDIDA, MATERIAS_PRIMAS.RECETA FROM stock LEFT JOIN MATERIAS_PRIMAS ON MATERIAS_PRIMAS.ID = STOCK.ID_MATERIAPRIMA WHERE STOCK.id_materiaprima = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			Materia mat = NullMateria.build();
			
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				mat = toMateria(resultados);
			}
			
			return mat;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Materia findByName(String nombre) {
		try {
			String sql = "SELECT MATERIAS_PRIMAS.ID, MATERIAS_PRIMAS.NOMBRE, MATERIAS_PRIMAS.CATEGORIA, MATERIAS_PRIMAS.TIPO_MATERIA, MATERIAS_PRIMAS.COSTO, MATERIAS_PRIMAS.CANTIDAD, STOCK.CANTIDAD, MATERIAS_PRIMAS.UNIDAD_DE_MEDIDA, MATERIAS_PRIMAS.RECETA FROM stock LEFT JOIN MATERIAS_PRIMAS ON MATERIAS_PRIMAS.ID = STOCK.ID_MATERIAPRIMA WHERE materias_primas.nombre = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);

			Materia mat = NullMateria.build();
			
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				mat = toMateria(resultados);
			}
			
			return mat;
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	private Materia toMateria(ResultSet stockRegister) throws SQLException {
		Materia materia = null;
		Double costoActualizado = ((stockRegister.getDouble(7) / stockRegister.getDouble(6)) * stockRegister.getDouble(5));
		switch (stockRegister.getString(4)) {
		case ("conmesurable"):
			materia = new MateriaConmesurable(stockRegister.getInt(1), stockRegister.getString(2),
					stockRegister.getString(3), stockRegister.getString(4), costoActualizado,
					stockRegister.getDouble(7), stockRegister.getString(8));
			break;
		case ("contable"):
			materia = new MateriaContable(stockRegister.getInt(1), stockRegister.getString(2),
					stockRegister.getString(3), stockRegister.getString(4), costoActualizado,
					stockRegister.getDouble(7));
			break;
		case ("elaborada"):
			if (stockRegister.getString(8) == null) {
				materia = new MateriaElaborada(stockRegister.getInt(1), stockRegister.getString(2),
						stockRegister.getString(3), stockRegister.getString(4), costoActualizado,
						stockRegister.getDouble(7), this.lectorDeRecetas(stockRegister.getString(9)));
			} else {
				materia = new MateriaElaborada(stockRegister.getInt(1), stockRegister.getString(2),
						stockRegister.getString(3), stockRegister.getString(4), costoActualizado,
						stockRegister.getDouble(7), stockRegister.getString(8),
						this.lectorDeRecetas(stockRegister.getString(9)));
			}
			break;
		default:
			// TODO
			break;
		}

		return materia;

	}

	private List<Materia> lectorDeRecetas(String receta) {

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

	public int insert(Materia mat) {
		try {
			String sql = "INSERT INTO STOCK (ID_MATERIAPRIMA, CANTIDAD, COSTO_TOTAL) VALUES (?, ? , ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, mat.getId());
			statement.setDouble(i++, mat.getCantidad());
			statement.setDouble(i++, mat.getCosto());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Materia materia) {
		try {
			String sql = "UPDATE STOCK SET CANTIDAD = ?, COSTO_TOTAL = ? WHERE ID_MATERIAPRIMA = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
		
			statement.setDouble(i++, materia.getCantidad());
			statement.setDouble(i++, materia.getCosto());

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
	
	@Override
	public Materia agregarAStock(Materia materia) {
		Materia tmpMateria = this.find(materia.getId()); //TRAE LA MATERIA GUARDADA EN BASE DE DATOS
		final Double nuevaCantidad = tmpMateria.getCantidad() + materia.getCantidad(); // SUMA LA CANTIDAD GUARDADA EN STOCK A LA CANTIDAD AGREGADA
		final Double nuevoCostoTotal = tmpMateria.getCosto() + materia.getCosto(); // SUMA EL COSTO GUARDADO EN STOCK CON EL COSTO DE LA MATERIA AGREGADA
		materia.setCantidad(nuevaCantidad);
		materia.setCosto(nuevoCostoTotal);
		try {
			String sql = "UPDATE STOCK SET CANTIDAD = ?, COSTO_TOTAL = ? WHERE ID_MATERIAPRIMA = ?"; //GUARDA LA NUEVA CANTIDAD EN BASE DE DATOS
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			
			statement.setDouble(i++, materia.getCantidad());
			statement.setDouble(i++, materia.getCosto());

			statement.setInt(i++, materia.getId());
			statement.executeUpdate();

			return materia;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	}


