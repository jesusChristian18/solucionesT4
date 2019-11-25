package com.tiendarop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiendarop.model.entity.Ciudad;
import com.tiendarop.model.repository.CiudadRepository;
import com.tiendarop.service.CiudadService;

@Service
public class CiudadServiceImpl implements CiudadService{
	@Autowired
	private CiudadRepository ciudadRepository;
	@Override
	public List<Ciudad> findAll() throws Exception {
		// TODO Auto-generated method stub
		return ciudadRepository.findAll();
	}

	@Override
	public Optional<Ciudad> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return ciudadRepository.findById(id);
	}
	@Transactional
	@Override
	public Ciudad save(Ciudad entity) throws Exception {
		// TODO Auto-generated method stub
		return ciudadRepository.save(entity);
	}
	@Transactional
	@Override
	public Ciudad update(Ciudad entity) throws Exception {
		// TODO Auto-generated method stub
		return ciudadRepository.save(entity);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		ciudadRepository.deleteById(id);
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		ciudadRepository.deleteAll();
	}

}
