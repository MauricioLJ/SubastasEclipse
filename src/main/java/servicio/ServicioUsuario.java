package servicio;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import model.Usuario;

public class ServicioUsuario extends Servicio {
	
	public void crearUsuario(Usuario usuario) {
        startTransaction();
        try {
            em.persist(usuario);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public Usuario leerUsuario(Integer id) {
        startTransaction();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    public void actualizarUsuario(Usuario usuario) {
        startTransaction();
        try {
            em.merge(usuario);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Integer id) {
        startTransaction();
        try {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            } else {
                throw new EntityNotFoundException("Usuario no encontrado con ID " + id);
            }
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
    
    public Usuario loginUsuario(String correo, String contrasena) {
        try {
            startTransaction();
            Usuario usuario = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena", Usuario.class)
                .setParameter("correo", correo)
                .setParameter("contrasena", contrasena)
                .getSingleResult();
            em.close();
            return usuario;
        } catch (NoResultException e) {
            em.close();
            return null;
        }
    }
	
}
