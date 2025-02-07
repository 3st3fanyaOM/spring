package com.example.repositorio;

import java.util.List;


import com.example.modelo.Cliente;

public interface ClienteRepository {
	List<Cliente> findAll();

	Cliente findById(Integer id);

	void save(Cliente cliente);

	void delete(Cliente cliente);

	//Cliente actualizar(Cliente cliente);

	List<Cliente> buscarNombresA();
	
	List<Cliente> buscarNombresPorLetra(String letra);
	
	List<Cliente> findByCiudad(String ciudad);

}
