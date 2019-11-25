package com.tiendarop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiendarop.model.entity.Categoria;
import com.tiendarop.model.entity.Producto;
import com.tiendarop.model.entity.Proveedor;
import com.tiendarop.model.repository.ProductoRepository;
import com.tiendarop.service.CategoriaService;
import com.tiendarop.service.ProductoService;
import com.tiendarop.service.ProveedorService;

@Controller
@RequestMapping("/producto")
@SessionAttributes({"producto","categoria", "proveedor"})
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private ProductoRepository productosRepository;
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/inicio";
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Producto> optional = productoService.findById(id);
				if(optional.isPresent()) {
					List<Categoria> categorias = categoriaService.findAll();
					List<Proveedor> proveedores = proveedorService.findAll();
					model.addAttribute("producto", optional.get());
					model.addAttribute("categorias", categorias);
					model.addAttribute("proveedores", proveedores);
				}
				else {
					return "redirect:/producto";
				}
				
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/producto/edit";
	}
	
	
	 @PostMapping(value = "/editar/{id}")
	    public String actualizarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	        if (bindingResult.hasErrors()) {
	            if (producto.getId() != null) {
	                return "productos/editar_producto";
	            }
	            return "redirect:/productos/mostrar";
	        }
	        Producto posibleProductoExistente = productosRepository.findFirstByCodigo(producto.getCodigo());

	        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/productos/agregar";
	        }
	        productosRepository.save(producto);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Editado correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/productos/mostrar";
	    }
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("producto") Producto producto, 
			Model model, SessionStatus status) {
		try {
			productoService.save(producto);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/producto";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		try {
			List<Categoria> categorias = categoriaService.findAll();
			List<Proveedor> proveedores = proveedorService.findAll();
			model.addAttribute("categorias", categorias);
			model.addAttribute("proveedores", proveedores);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "/producto/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if(producto.isPresent()) {
				productoService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Producto> producto = productoService.findAll();
				model.addAttribute("producto", producto);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/producto/inicio";
		}
		return "redirect:/producto";
	}
	
	@PostMapping(value = "/agregar")
    public String guardarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (productosRepository.findFirstByCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe ese codigo")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }
	
	
	
	
	
	
}


