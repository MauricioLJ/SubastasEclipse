package servicio;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import model.Subasta;
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
    
    public boolean existeCorreo(String correo) {
        try {
            startTransaction();
            long count = em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.correo = :correo", Long.class)
                    .setParameter("correo", correo)
                    .getSingleResult();
            em.close();
            return count > 0;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return false;
        }
    }

    public void actualizarCalificacion(Usuario usuario, Integer nuevaCalificacion) {
        startTransaction();
        try {
            int cantidad = (usuario.getCantidadCalificaciones() == null) ? 0 : usuario.getCantidadCalificaciones();
            double promedioActual = (usuario.getCalificacionPromedio() == null) ? 0.0 : usuario.getCalificacionPromedio();

            double nuevoPromedio = ((promedioActual * cantidad) + nuevaCalificacion) / (cantidad + 1);
            usuario.setCalificacionPromedio(nuevoPromedio);
            usuario.setCantidadCalificaciones(cantidad + 1);

            em.merge(usuario); 
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();  
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios() {
        startTransaction();  
        List<Usuario> usuario = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList(); 
        em.close();
        return usuario; 
    }
    
    public void actualizarClavePorCorreo(String correo, String nuevaClave) {
        startTransaction();
        try {
            // Buscar al usuario por correo
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                                 .setParameter("correo", correo)
                                 .getSingleResult();

            if (usuario != null) {
                usuario.setContrasena(nuevaClave); // Actualizar la contraseña
                em.merge(usuario); // Guardar el cambio en la base de datos
            }

            commitTransaction();
        } catch (NoResultException e) {
            rollbackTransaction();
            throw new EntityNotFoundException("Usuario no encontrado con el correo: " + correo);
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
    
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        try {
            startTransaction();
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                                .setParameter("correo", correo)
                                .getSingleResult();
            em.close();
            return usuario;
        } catch (NoResultException e) {
            em.close();
            return null; // Si no encuentra un usuario, devuelve null
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null; // Manejo de excepciones generales
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        try {
            startTransaction();
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario", Usuario.class)
                                .setParameter("idUsuario", idUsuario)
                                .getSingleResult();
            em.close();
            return usuario;
        } catch (NoResultException e) {
            em.close();
            return null; // Si no encuentra un usuario, devuelve null
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null; // Manejo de excepciones generales
        }
        
        
    }

    
}
