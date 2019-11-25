package com.tiendarop.service;

import java.util.Optional;

import com.tiendarop.model.entity.Usuario;



public interface UsuarioService extends CrudService<Usuario, Long> {
	Optional<Usuario> findByUsername(String username) throws Exception;
}
