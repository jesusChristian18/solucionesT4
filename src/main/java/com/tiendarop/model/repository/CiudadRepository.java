package com.tiendarop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendarop.model.entity.Ciudad;




@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

}
