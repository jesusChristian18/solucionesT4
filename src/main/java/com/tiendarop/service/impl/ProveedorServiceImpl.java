package com.tiendarop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import  com.tiendarop.model.entity.Proveedor;

import  com.tiendarop.model.repository.ProveedorRepository;

import  com.tiendarop.service.ProveedorService;
@Service

public class ProveedorServiceImpl implements ProveedorService {
	@Autowired
	private ProveedorRepository proveedorRepository;
	@Transactional(readOnly = true)
	@Override
	public List<Proveedor> findAll() throws Exception {
		// TODO Auto-generated method stub
		return proveedorRepository.findAll();
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Proveedor> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return proveedorRepository.findById(id);
	}
	@Transactional
	@Override
	public Proveedor save(Proveedor entity) throws Exception {
		// TODO Auto-generated method stub
		return proveedorRepository.save(entity);
	}
	@Transactional
	@Override
	public Proveedor update(Proveedor entity) throws Exception {
		// TODO Auto-generated method stub
		return proveedorRepository.save(entity);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		proveedorRepository.deleteById(id);
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		proveedorRepository.deleteAll();
		
	}
	

}
