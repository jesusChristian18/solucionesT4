package com.tiendarop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiendarop.model.entity.Producto;
import com.tiendarop.model.entity.ProductoParaVender;
import com.tiendarop.model.entity.ProductoVendido;
import com.tiendarop.model.entity.Venta;
import com.tiendarop.model.repository.ProductoRepository;
import com.tiendarop.model.repository.ProductosVendidosRepository;
import com.tiendarop.model.repository.VentasRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/vender")
public class VenderController {
    @Autowired
    private ProductoRepository productosRepository;
    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private ProductosVendidosRepository productosVendidosRepository;

    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.guardarCarrito(carrito, request);
        }
        return "redirect:/vender/";
    }

    private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }

    @GetMapping(value = "/limpiar")
    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }

    @PostMapping(value = "/terminar")
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        if (carrito == null || carrito.size() <= 0) {
            return "redirect:/vender/";
        }
        Venta ve = ventasRepository.save(new Venta());
        for (ProductoParaVender productoParaVender : carrito) {
            Producto pro = productosRepository.findById(productoParaVender.getId()).orElse(null);
            if (pro == null) continue;            
            pro.restarExistencia(productoParaVender.getCantidad());
            productosRepository.save(pro);
            ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(), productoParaVender.getPrecio(), productoParaVender.getNombre(), productoParaVender.getCodigo(), ve);
            productosVendidosRepository.save(productoVendido);
        }
        this.limpiarCarrito(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }

    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
        model.addAttribute("producto", new Producto());
        float total = 0;
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        for (ProductoParaVender pro: carrito) total += pro.getTotal();
        model.addAttribute("total", total);
        return "vender/vender";
    }

    private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }

    @PostMapping(value = "/agregar")
    public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
        Producto productoBuscadoPorCodigo = productosRepository.findFirstByCodigo(producto.getCodigo());
        if (productoBuscadoPorCodigo == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto de dicho  código " + producto.getCodigo() + " no existe, vuelve a intentarlo!!!")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        if (productoBuscadoPorCodigo.sinExistencia()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto agotado, vuelve a intentarlo!!!")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        boolean encontrado = false;
        for (ProductoParaVender productoParaVenderActual : carrito) {
            if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getNombre(), productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getPrecio(), productoBuscadoPorCodigo.getExistencia(), productoBuscadoPorCodigo.getId(),1f));
        }
        this.guardarCarrito(carrito, request);
        return "redirect:/vender/";
    }
 
}
