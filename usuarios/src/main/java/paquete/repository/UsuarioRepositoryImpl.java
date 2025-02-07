package paquete.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import paquete.model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

	 @Override
    public List<Usuario> getAllUsuarios() {
        return entityManager.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void saveUsuario(Usuario usuario) {
    	
        if (usuario.getId() == null) {        	
            entityManager.persist(usuario);            
        } else {
            entityManager.merge(usuario);
        }
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
    	
       entityManager.remove(usuario);
    }

    @Override
    public List<Usuario> usuariosBiografia(String palabra) {
        String hql = "SELECT u FROM Usuario u WHERE u.perfil.bio LIKE :palabra";
        TypedQuery<Usuario> query = entityManager.createQuery(hql, Usuario.class);
        query.setParameter("palabra", "%" + palabra + "%");
        return query.getResultList();
    }
    
    
    @Override
    public Usuario primerUsuarioDisponible() {
    	 String hql = "SELECT u FROM Usuario u WHERE u.perfil.estado = :estado";
         TypedQuery<Usuario> query = entityManager.createQuery(hql, Usuario.class);
         query.setParameter("estado", "DISPONIBLE");
         
         List<Usuario> usuarios = query.getResultList();
         return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    
    
    @Override       
    public List<Usuario> usuariosNoDisponibles() {
    	String hql = "SELECT u FROM Usuario u WHERE u.perfil.estado = :estado";
        return entityManager.createQuery(hql, Usuario.class).setParameter("estado", "NO DISPONIBLE").getResultList();
    }

}

