package com.tiendarop.model.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "proveedores")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombreProveedor", length = 60)
	private String nombre;

	@Column(name = "apellidoProveedor", length = 60)
	private String apellido;
	
	@Column(name = "nombreEmpresa", length = 60)
	private String empresa;
	
	@Column(name = "telefonoProveedor", length = 10)
	private String telefono;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudad_id")
	private Ciudad ciudad;
	
	@OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
	private List<Producto> productos;
	
	public Proveedor() {
		productos = new ArrayList<>();
	}
	
	public void addProducto(Producto producto) {
		producto.setProveedor(this);
		this.productos.add(producto);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	

	
}
