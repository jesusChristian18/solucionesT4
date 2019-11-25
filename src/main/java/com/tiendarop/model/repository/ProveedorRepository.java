package com.tiendarop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendarop.model.entity.Proveedor;



@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	

}
