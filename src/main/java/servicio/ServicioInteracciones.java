package servicio;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Interacciones;
import model.Interacciones.TipoInteraccion;
import model.Subasta;

public class ServicioInteracciones extends Servicio implements Serializable{

	public void registrarInteraccion(Interacciones interaccion) {
	    try {
	        startTransaction();
	        em.persist(interaccion);
	        commitTransaction();
	    } catch (Exception e) {
	        rollbackTransaction(); 
	        e.printStackTrace();
	    } 
	}

	public List<String> obtenerCategoriasFrecuentesPorUsuario(int idUsuario) {
	    try {
	        TypedQuery<String> query = em.createQuery(
	            "SELECT c.nombre " +
	            "FROM Interacciones i " +
	            "JOIN i.subasta s " +
	            "JOIN s.categorias c " +
	            "WHERE i.usuario.idUsuario = :idUsuario " +
	            "GROUP BY c.nombre " +
	            "ORDER BY COUNT(i.idInteraccion) DESC", String.class
	        );
	        query.setParameter("idUsuario", idUsuario);
	        return query.setMaxResults(20).getResultList(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}


    

}
