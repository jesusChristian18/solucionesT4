package com.tiendarop.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ciudad")
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombreCiudad", length = 50)
	private String nombre;



	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
	private List<Proveedor> proveedores;
	
	public Ciudad() {
		proveedores = new ArrayList<>();
	}
	
	public void addProveedor(Proveedor proveedor) {
		proveedor.setCiudad(this);
		this.proveedores.add(proveedor);
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

	
}
