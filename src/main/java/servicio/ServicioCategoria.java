package servicio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import model.Categoria;

public class ServicioCategoria extends Servicio implements Serializable{

	public void crearCategoria(Categoria categoria) {
        startTransaction();
        try {
            em.persist(categoria);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public Categoria leerCategoria(Integer id) {
        startTransaction();
        Categoria categoria = em.find(Categoria.class, id);
        em.close();
        return categoria;
    }

    public void actualizarCategoria(Categoria categoria) {
        startTransaction();
        try {
            em.merge(categoria);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void eliminarCategoria(Integer id) {
        startTransaction();
        try {
        	 Categoria categoria = em.find(Categoria.class, id);
            if (categoria != null) {
                em.remove(categoria);
            } else {
                throw new EntityNotFoundException("Categoria no encontrada con ID " + id);
            }
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
    
    public List<Categoria> listarCategorias() {
        startTransaction();  
        List<Categoria> categoria = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        em.close();
        return categoria; 
    }

}
