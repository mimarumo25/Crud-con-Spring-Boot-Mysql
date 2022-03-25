package com.choucair.crud.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choucair.crud.model.Producto;
import com.choucair.crud.repository.ProductoRepository;

@Controller
@RequestMapping("/productos")// ruta de acceso a productos
public class ProductoController {
	private final Logger logger = LoggerFactory.getLogger(Producto.class);
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "home";
	}
	@GetMapping("/create")
	public String create(){
		return "create";
	}
	@PostMapping("/save")
	public String save(Producto producto){
		logger.info("msg, {}", producto);
		productoRepository.save(producto);
		return "redirect:/productos";
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model){
		Producto p = productoRepository.getById(id);
		model.addAttribute("producto", p);
		return "edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		Producto p = productoRepository.getById(id);
		logger.info("Objeto eliminado {}", p);
		productoRepository.delete(p);
		return "redirect:/productos";
	}
}
