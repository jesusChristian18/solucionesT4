package com.tiendarop.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiendarop.model.entity.Usuario;
import com.tiendarop.model.repository.AuthorityRepository;
import com.tiendarop.model.repository.UsuarioRepository;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		this.usuarioRepository.deleteAll();
		this.authorityRepository.deleteAll();
		
		Usuario cliente = new Usuario();
		cliente.setUsername("jean");
		cliente.setPassword(passwordEncoder.encode("jean"));
		cliente.setApellidos("Mendez");
		cliente.setNombres("Jean");
		cliente.setEnable(true);
		
		Usuario admin = new Usuario();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setApellidos("Zevallos");
		admin.setNombres("Chris");
        admin.setEnable(true);
		
        Usuario manager = new Usuario();
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder.encode("manager"));
        manager.setApellidos("Sierra");
		manager.setNombres("Alex");
		manager.setEnable(true);
        
		cliente.addAuthority("ROLE_CUSTOMER");
        admin.addAuthority("ROLE_EMPLOYEE");
        manager.addAuthority("ROLE_MANAGER");
        
        List<Usuario> usuarios = Arrays.asList(cliente, admin, manager);        
        this.usuarioRepository.saveAll(usuarios);
	}
}
