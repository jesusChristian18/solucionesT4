package com.tiendarop.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tiendarop.model.entity.Categoria;
import com.tiendarop.service.CategoriaService;


@Controller
@RequestMapping("/categoria")
@SessionAttributes("categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Categoria> categorias = categoriaService.findAll();
			model.addAttribute("categorias",categorias);
		} catch (Exception e) {
		}
		return "/categoria/inicio";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria",categoria);
		
		return "/categoria/nuevo";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer  id, Model model) {
		try {
			Optional<Categoria> optional = categoriaService.findById(id);
			if (optional.isPresent()) {
				
				
				model.addAttribute("categoria", optional.get());
			
			} else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {
		}
		
		return "/categoria/edit";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				categoriaService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Categoria> categoria= categoriaService.findAll();
				model.addAttribute("categoria",categoria);
			} catch (Exception e2) {
				
			} 
			return "/categoria/inicio";
		}
		return "redirect:/categoria";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("categoria") Categoria categoria, 
			Model model, SessionStatus status) {
		try {
			categoriaService.save(categoria);
			status.setComplete();
			
		} catch (Exception e) {
		
		}
		return "redirect:/categoria";
	}
	
	
}
