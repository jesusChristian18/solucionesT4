package com.tiendarop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendarop.model.entity.Authority;



@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
