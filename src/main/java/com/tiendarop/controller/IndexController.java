package com.tiendarop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiendarop.model.entity.Usuario;
import com.tiendarop.service.UsuarioService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public String index() {
		return "index";	
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("verid")
	public String verId(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			Optional<Usuario> optional 
				= usuarioService.findByUsername(username);
			if(optional.isPresent()) {
				model.addAttribute("usuario", optional.get());
			}
		} catch (Exception e) {
		}
		
		return "/verid";
		
	}

}
