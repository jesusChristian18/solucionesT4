package com.tiendarop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendarop.model.entity.Categoria;




@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
