package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TesterLogin {
    private static EntityManager em = null;
    private static EntityManagerFactory entityManagerFactory = null;

    public static void main(String[] args) {
        try {
            startEntityManagerFactory("Proyecto Subasta");
            em = entityManagerFactory.createEntityManager();
            
            // Llamada al método de login
            if (login("kate@gmail.com", "1112")) {
                System.out.println("Login exitoso.");
            } else {
                System.out.println("Credenciales incorrectas.");
            }
            
            stopEntityManagerFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String correo, String contrasena) {
        boolean autenticado = false;
        try {
            em.getTransaction().begin();

            // Consulta de autenticación
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena", 
                Usuario.class
            );
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            
            // Ejecuta la consulta
            Usuario usuario = query.getSingleResult();
            autenticado = usuario != null; // Si encuentra un usuario, autenticación es exitosa
            
            em.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Usuario no encontrado.");
            em.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        
        return autenticado;
    }

    public static void startEntityManagerFactory(String persistenceUnit) throws Exception {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopEntityManagerFactory() throws Exception {
        if (entityManagerFactory != null) {
            if (entityManagerFactory.isOpen()) {
                try {
                    entityManagerFactory.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            entityManagerFactory = null;
        }
    }
}
