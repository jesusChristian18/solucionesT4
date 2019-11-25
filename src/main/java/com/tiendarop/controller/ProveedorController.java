package com.tiendarop.controller;

import java.util.List;
import java.util.Optional;

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


import com.tiendarop.model.entity.Ciudad;
import com.tiendarop.model.entity.Proveedor;
import com.tiendarop.service.CiudadService;
import com.tiendarop.service.ProveedorService;


@Controller
@RequestMapping("/proveedor")
@SessionAttributes({"proveedor","ciudad"})


public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private CiudadService ciudadService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Proveedor> proveedores = proveedorService.findAll();
			model.addAttribute("proveedores", proveedores);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/proveedor/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Proveedor> optional = proveedorService.findById(id);
			if(optional.isPresent()) {
				List<Ciudad> ciudades = ciudadService.findAll();
				model.addAttribute("proveedor", optional.get());
				model.addAttribute("ciudades", ciudades);
			}
			else {
				return "redirect:/proveedor";
			}
				
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/proveedor/edit";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("proveedor") Proveedor proveedor, 
			Model model, SessionStatus status) {
		try {
			proveedorService.save(proveedor);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/proveedor";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Proveedor proveedor = new Proveedor();
		model.addAttribute("proveedor", proveedor);
		try {
			List<Ciudad> ciudades = ciudadService.findAll();
			model.addAttribute("ciudades", ciudades);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/proveedor/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Proveedor> proveedor = proveedorService.findById(id);
			if(proveedor.isPresent()) {
				proveedorService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Proveedor> proveedor = proveedorService.findAll();
				model.addAttribute("proveedor",proveedor);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/proveedor/inicio";
		}
		return "redirect:/proveedor";
	}
	
	
	
	
	
	
}


