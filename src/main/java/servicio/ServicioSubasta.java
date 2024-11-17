package servicio;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import model.Subasta;

public class ServicioSubasta extends Servicio implements Serializable {

  public void crearSubasta(Subasta subasta) {
        startTransaction();
        try {
            em.persist(subasta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public Subasta leerSubasta(Integer id) {
        startTransaction();
        Subasta subasta = em.find(Subasta.class, id);
        em.close();
        return subasta;
    }

    public void actualizarSubasta(Subasta subasta) {
        startTransaction();
        try {
            em.merge(subasta);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void eliminarSubasta(Integer id) {
        startTransaction();
        try {
            Subasta subasta = em.find(Subasta.class, id);
            if (subasta != null) {
                em.remove(subasta);
            } else {
                throw new EntityNotFoundException("Subasta no encontrada con ID " + id);
            }
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
    
    public List<Subasta> listarSubastas() {
        startTransaction();  
        List<Subasta> subastas = em.createQuery("SELECT s FROM Subasta s", Subasta.class).getResultList(); 
        em.close();
        return subastas; 
    }

}
