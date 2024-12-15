package servicio;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import model.Notificacion;

public class ServicioNotificacion extends Servicio implements Serializable {

	public void crearNotificacion(Notificacion notificacion) {
		startTransaction();
		try {
			em.persist(notificacion);
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
	}

	public Notificacion leerNotificacion(Integer id) {
		startTransaction();
		Notificacion notificacion = em.find(Notificacion.class, id);
		em.close();
		return notificacion;
	}

	public void actualizarNotificacion(Notificacion notificacion) {
		startTransaction();
		try {
			em.merge(notificacion);
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
	}

	public void eliminarNotificacion(Integer id) {
		startTransaction();
		try {
			Notificacion notificacion = em.find(Notificacion.class, id);
			if (notificacion != null) {
				em.remove(notificacion);
			} else {
				throw new EntityNotFoundException("Notificacion no encontrada con ID " + id);
			}
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
	}

	public List<Notificacion> listarNotificaciones() {
		startTransaction();
		try {
			TypedQuery<Notificacion> query = em.createNamedQuery("Notificacion.listarTodas", Notificacion.class);
			List<Notificacion> notificaciones = query.getResultList();
			em.close();
			return notificaciones;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
			return Collections.emptyList();
		}
	}
	
	public List<Notificacion> obtenerNotificacionesNoLeidas(Integer usuarioId) {
        startTransaction();
        try {
            TypedQuery<Notificacion> query = em.createNamedQuery("Notificacion.noLeidas", Notificacion.class);
            query.setParameter("idUsuario", usuarioId);
            List<Notificacion> notificaciones = query.getResultList();
            em.close();
            return notificaciones;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            return Collections.emptyList();
        }
    }
}