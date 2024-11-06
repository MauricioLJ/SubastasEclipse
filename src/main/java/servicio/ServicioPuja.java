package servicio;

import javax.persistence.EntityNotFoundException;

import model.Puja;

public class ServicioPuja extends Servicio {

	public void crearPuja(Puja puja) {
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
	
}
