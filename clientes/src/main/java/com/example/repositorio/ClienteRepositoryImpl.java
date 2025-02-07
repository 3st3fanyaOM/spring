package com.example.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.modelo.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository{
	@PersistenceContext
    private EntityManager entityManager;

    public List<Cliente> findAll() {
        return entityManager.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

    public Cliente findById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    public void save(Cliente cliente) {
    	
        if (cliente.getId() == null) {
        	
            entityManager.persist(cliente);
            System.out.println("ha guardado?");
        } else {
            entityManager.merge(cliente);
        }
    }

    public void delete(Cliente cliente) {
    	
        entityManager.remove(cliente);
    }

	/*@Override
	public Cliente actualizar(Cliente cliente) {
		
		
		return entityManager.merge(cliente);		
	}*/
	
	@Override
	public List<Cliente> buscarNombresA(){
		 return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :prefix", Cliente.class)
         .setParameter("prefix", "A" + "%")
         .getResultList();
	}
	
	@Override
	public List<Cliente> buscarNombresPorLetra(String letra){
		
		System.out.println("la letra es " + letra);
		 return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :prefix", Cliente.class)
        .setParameter("prefix", letra + "%")
        .getResultList();
	}
	
	@Override
	 public List<Cliente> findByCiudad(String ciudad) {
		
	        String query = "SELECT c FROM Cliente c WHERE c.direccion.ciudad = :ciudad";
	        return entityManager.createQuery(query, Cliente.class)
	                            .setParameter("ciudad", ciudad)
	                            .getResultList();
	    
		
	    }

}
