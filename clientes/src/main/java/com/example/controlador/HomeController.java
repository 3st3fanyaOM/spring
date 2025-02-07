package com.example.controlador;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.modelo.Cliente;
import com.example.servicio.ClienteService;

@Controller
public class HomeController {

	@Autowired
	ClienteService servicio;

	@GetMapping
	public String getClientes(Model model) {
		model.addAttribute("clientes", servicio.getAllClientes());
		return "index";
	}
	
	@GetMapping("/{id}")
	public String detallesCliente(@PathVariable Integer id, Model model) {
		model.addAttribute("cliente",servicio.getClienteById(id));
		return "cliente-detalle";
	}
	
	@GetMapping("/cliente-formulario")
	public String form(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente-formulario";
	}
	
	@PostMapping("cliente-formulario")
	public String submitForm(@ModelAttribute Cliente cliente, Model model) {
		model.addAttribute("mensaje","Cliente registrado");
		servicio.saveCliente(cliente);
		return "result";
	}
	
	@GetMapping("/ciudad")
	public String mostrarFormulario() {
		return "ciudad";
	}
	
	@PostMapping("/ciudad") //post por que recibe datos por formulario
	public String buscarPorCiudad(@RequestParam String ciudad, Model model) {
		System.out.println("Buscando clientes para la ciudad: " + ciudad);
		List<Cliente> clientes = servicio.findClientesByCiudad(ciudad);
	    model.addAttribute("clientes", clientes);
	    model.addAttribute("ciudadBuscada", ciudad);
		return "ciudad";
	}
	
	
	
}
