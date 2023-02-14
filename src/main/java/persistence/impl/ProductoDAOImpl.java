package persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.nullobjects.NullProducto;
import model.system.materiaprima.Materia;
import model.system.materiaprima.MateriaConmesurable;
import model.system.materiaprima.MateriaContable;
import model.system.materiaprima.MateriaElaborada;
import model.system.tienda.Producto;
import persistence.ProductoDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class ProductoDAOImpl implements ProductoDAO {

	@Override
	public List<Producto> findAll() {
		try {
			String sql = "SELECT * FROM PRODUCTOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Producto> productos = new LinkedList<Producto>();

			while (resultados.next()) {
				productos.add(toProducto(resultados));
			}

			return productos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Producto find(Integer id) {
		try {
			String sql = "SELECT * FROM PRODUCTOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Producto producto = NullProducto.build();
			if (resultados.next()) {
				producto = toProducto(resultados);
			}

			return producto;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Producto toProducto(ResultSet productoRegister) throws SQLException {


		
		return new Producto(productoRegister.getInt(1), productoRegister.getString(2), productoRegister.getString(3), productoRegister.getInt(4),
				productoRegister.getDouble(5), productoRegister.getDate(6),
				lectorDeRecetas(productoRegister.getString(7)));
	}

	@Override
	public int insert(Producto prod) {
		try {

			Date sqlDate = new Date(prod.getFechaDeCreacion().getTime());

			String sql = "INSERT INTO PRODUCTOS (NOMBRE, DESCRIPCION, CATEGORIA_ID, COSTO_DE_PRODUCCION, FECHA_DE_CREACION, INGREDIENTES) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, prod.getNombre());
			statement.setString(i++, prod.getDescripcion());
			statement.setInt(i++, prod.getCategoriaId());
			statement.setDouble(i++, prod.getCostoDeProduccion());
			statement.setDate(i++, sqlDate);
			statement.setString(i++, prod.getIngredientesTextoPlano());

			return statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int update(Producto prod) {
		try {
			String sql = "UPDATE PRODUCTOS SET NOMBRE = ?, DESCRIPCION = ?, CATEGORIA_ID = ?,COSTO_DE_PRODUCCION = ?, INGREDIENTES = ?, WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, prod.getNombre());
			statement.setString(i++, prod.getDescripcion());
			statement.setInt(i++, prod.getCategoriaId());
			statement.setDouble(i++, prod.getCostoDeProduccion());
			statement.setString(i++, prod.getIngredientesTextoPlano());
			
			statement.setInt(i++, prod.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Producto prod) {
		try {
			String sql = "DELETE FROM PRODUCTOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, prod.getId());

			return statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {

			String sql = "SELECT COUNT(1) AS TOTAL FROM PRODUCTOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();

			return resultados.getInt("TOTAL");

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// OTROS METODOS 

	@Override
	public Producto findByName(String nombre) {
		try {
			String sql = "SELECT * FROM PRODUCTOS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);

			ResultSet resultados = statement.executeQuery();

			Producto producto = NullProducto.build();
			if (resultados.next()) {
				producto = toProducto(resultados);
			}

			return producto;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Materia> lectorDeRecetas(String receta) {
		
		List<Materia> res = new LinkedList<Materia>();	
		
		if(!receta.isEmpty()) {
			
			String[] listaDeIngredientes = receta.split("/");

			for (int i = 0; i < listaDeIngredientes.length; i++) {
				String[] ingrediente = listaDeIngredientes[i].split("-");
				Materia tmp_materia = DAOFactory.getMateriaDAO().find(Integer.parseInt(ingrediente[0]));
				Double costoIngrediente = (Double.parseDouble(ingrediente[1]) * tmp_materia.getPrecioMinimo());
				Materia tmp_materia2;
				switch (tmp_materia.getTipo()) {

				case ("conmesurable"):
					tmp_materia2 = new MateriaConmesurable(tmp_materia.getId(), tmp_materia.getNombre(),
							tmp_materia.getCategoriaPadre(), tmp_materia.getTipo(), costoIngrediente,
							Double.parseDouble(ingrediente[1]), ingrediente[2]);
					res.add(tmp_materia2);
					break;
				case ("contable"):
					tmp_materia2 = new MateriaContable(tmp_materia.getId(), tmp_materia.getNombre(),
							tmp_materia.getCategoriaPadre(), tmp_materia.getTipo(), costoIngrediente,
							Double.parseDouble(ingrediente[1]));
					res.add(tmp_materia2);
					break;
				case ("elaborada"):
					// REVISA SI LA MATERIA ELABORADA ES CONMESURABLE O CONTABLE
					if (tmp_materia.getUnidadDeMedida() == null) {
						tmp_materia2 = new MateriaElaborada(tmp_materia.getId(), tmp_materia.getNombre(),
								tmp_materia.getCategoriaPadre(), tmp_materia.getTipo(), costoIngrediente,
								tmp_materia.getCantidad(), this.lectorDeRecetas(ingrediente[1]));

					} else {
						tmp_materia2 = new MateriaElaborada(tmp_materia.getId(), tmp_materia.getNombre(),
								tmp_materia.getCategoriaPadre(), tmp_materia.getTipo(), costoIngrediente,
								tmp_materia.getCantidad(), ingrediente[1], this.lectorDeRecetas(ingrediente[2]));
					}
					res.add(tmp_materia2);
					break;

				}

			}
			
		}
		
	

	

		return res;
	}

}