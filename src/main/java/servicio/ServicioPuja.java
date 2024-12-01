package servicio;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import model.Puja;
import model.Subasta;

public class ServicioPuja extends Servicio implements Serializable {

	public void crearPuja(Puja puja) {
	    if (puja == null) {
	        throw new IllegalArgumentException("Puja no puede ser nula");
	    }

	    startTransaction();
	    try {
	        em.persist(puja);
	        commitTransaction();
	    } catch (Exception e) {
	        rollbackTransaction();
	        e.printStackTrace();
	    }
	}

	public Puja leerPuja(Integer id) {
		startTransaction();
		Puja puja = em.find(Puja.class, id);
		em.close();
		return puja;
	}

	public void actualizarPuja(Puja puja) {
		startTransaction();
		try {
			em.merge(puja);
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
	}

	public void eliminarPuja(Integer id) {
		startTransaction();
		try {
			Puja puja = em.find(Puja.class, id);
			if (puja != null) {
				em.remove(puja);
			} else {
				throw new EntityNotFoundException("Puja no encontrada con ID " + id);
			}
			commitTransaction();
		} catch (Exception e) {
			rollbackTransaction();
			e.printStackTrace();
		}
	}

	public List<Puja> listarPujas() {
		startTransaction();
		try {
			TypedQuery<Puja> query = em.createNamedQuery("Puja.listarTodas", Puja.class);
			List<Puja> pujas = query.getResultList();
			em.close();
			return pujas;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
			return Collections.emptyList();
		}
	}

	public List<Puja> listaPujasPorSubasta(int idSubasta) {
	    // Optional: Validate auction exists first
	    Subasta subasta = em.find(Subasta.class, idSubasta);
	    if (subasta == null) {
	        return Collections.emptyList();
	    }

	    startTransaction();
	    try {
	        TypedQuery<Puja> query = em.createNamedQuery("Puja.porSubasta", Puja.class);
	        query.setParameter("idSubasta", idSubasta);
	        List<Puja> pujas = query.getResultList();
	        em.close();
	        return pujas;
	    } catch (Exception e) {
	        e.printStackTrace();
	        rollbackTransaction();
	        return Collections.emptyList();
	    }
	}
}
