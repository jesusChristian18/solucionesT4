package com.tiendarop.controller;
import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tiendarop.model.entity.Ciudad;
import com.tiendarop.service.CiudadService;

@Controller
@RequestMapping("/ciudad")
@SessionAttributes("ciudad")
public class CiudadController {
	@Autowired
	private CiudadService ciudadService;
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Ciudad> ciudades = ciudadService.findAll();
			model.addAttribute("ciudades",ciudades);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/ciudad/inicio";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Ciudad ciudad = new Ciudad();
		model.addAttribute("ciudad",ciudad);
		
		return "/ciudad/nuevo";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer  id, Model model) {
		try {
			Optional<Ciudad> optional = ciudadService.findById(id);
			if (optional.isPresent()) {
				
				
				model.addAttribute("ciudad", optional.get());
			
			} else {
				return "redirect:/ciudad";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/ciudad/edit";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Ciudad> ciudad = ciudadService.findById(id);
			if(ciudad.isPresent()) {
				ciudadService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - ViolaciÃ³n contra el principio de Integridad referencia");
			try {
				List<Ciudad> ciudad= ciudadService.findAll();
				model.addAttribute("ciudad",ciudad);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/ciudad/inicio";
		}
		return "redirect:/ciudad";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("ciudad") Ciudad ciudad, 
			Model model, SessionStatus status) {
		try {
			ciudadService.save(ciudad);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/ciudad";
	}
	
	
}

