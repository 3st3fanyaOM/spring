package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioService servicio;

	@GetMapping("/listarUsuarios")
	public String getClientes(Model model) {
		model.addAttribute("usuarios", servicio.getAllUsuarios());
		return "usuarios";
	}
	
}
