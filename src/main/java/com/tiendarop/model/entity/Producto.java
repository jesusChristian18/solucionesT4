package com.tiendarop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombreProducto", length = 60)
	private String nombre;
	
	@Column(name = "codigoNombre", length = 60)
	private String codigo;
	

	@Column(name = "existenciaProducto")
	private Float existencia;
	

	@Column(name = "precioProducto")
	private Float precio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venta_id")
	private Venta venta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedor;
	
	//*********************************************************************
	
	 public Producto(String nombre, String codigo, Float precio,Float existencia, Integer id) {
	        this.nombre = nombre;
	        this.codigo = codigo;
	        this.precio = precio;
	        this.existencia = existencia;
	        this.id = id;
	    }
	 
	
	 public Producto(String nombre, String codigo, Float precio,Float existencia) {
	        this.nombre = nombre;
	        this.codigo = codigo;
	        this.precio = precio;
	        this.existencia = existencia;
	    }
	
	
	 public Producto(@NotNull(message = "Especificar el código") @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50") String codigo) {
	        this.codigo = codigo;
	    }
	 
	 
	 public Producto() {
	    }
	
	 public boolean sinExistencia() {
	        return this.existencia <= 0;
	    }

	 //***************************************************************
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getExistencia() {
		return existencia;
	}

	public void setExistencia(Float existencia) {
		this.existencia = existencia;
	}
	
	public void restarExistencia(Float existencia) {
        this.existencia -= existencia;
    }
	
	
	
	
}
