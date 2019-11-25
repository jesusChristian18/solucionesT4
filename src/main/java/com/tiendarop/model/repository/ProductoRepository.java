package com.tiendarop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendarop.model.entity.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Producto findFirstByCodigo(String codigo);

}
