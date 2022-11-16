package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.system.tienda.Producto;
import utils.Crypt;

public class Usuario {

	private Integer id;
	private String username;
	private String password;
	private Long telefono;
	private String direccion;
	private List<Producto> compras;
	private Date fechadeRegistro;
	private Boolean admin;
	private HashMap<String, String> errors;
	
	public Usuario(Integer id, String username, String password, Long telefono, String direccion, Boolean admin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fechadeRegistro = new Date(System.currentTimeMillis());
		this.admin = admin;

	}

	public boolean isNull() {
		return false;
	}

	public boolean checkPassword(String password) {
		return Crypt.match(password, this.password);

	}

	// GETTERS Y GETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Producto> getCompras() {
		return compras;
	}

	public void setCompras(List<Producto> compras) {
		this.compras = compras;
	}

	public Date getFechadeRegistro() {
		return fechadeRegistro;
	}

	public void setFechadeRegistro(Date fechadeRegistro) {
		this.fechadeRegistro = fechadeRegistro;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (username.equals("")) {
			errors.put("username", "El email ingresado es invalido.");
		}
		
		if (telefono == null) {
			errors.put("telefono", "El telefono ingresado es invalido.");
		}
		if (direccion.equals("")) {
			errors.put("direccion", "La direccion ingresada es invalida.");
		}
		
		
		
	}
	
	
}
