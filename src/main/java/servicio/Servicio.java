package servicio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Servicio {

	protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager em;
    
    public Servicio() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("Proyecto Subasta"); // Asegúrate que coincide con el nombre en persistence.xml
        }
        em = entityManagerFactory.createEntityManager();
    }

    public static void startEntityManagerFactory(String persistenceUnit) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        }
    }

    public static void stopEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    protected void startTransaction() {
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
    }

    protected void commitTransaction() {
        em.getTransaction().commit();
        em.close();
    }

    protected void rollbackTransaction() {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        if (em != null) {
            em.close();
        }
    }
	
}
