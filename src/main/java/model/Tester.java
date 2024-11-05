package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {
	private static EntityManager em = null;
	private static EntityManagerFactory entityManagerFactory = null;

	public static void main(String[] args) {
		try {
			System.out.println("Registrando");
			startEntityManagerFactory("Proyecto Subasta");
			em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			

			//Usuario usuario = new Usuario();
			//usuario.setNombre("Katelyn");
			//usuario.setApellidos("Alleyne");
			//usuario.setCorreo("kate@gmail.com");
			//usuario.setContrasena("1111");
			//em.persist(usuario); 
			
			//PRUEBA NANA
			//Usuario usuarioUpdate = em.find(Usuario.class, 3);
			//usuarioUpdate.setCorreo("dariii@gmail.com");
			//em.merge(usuarioUpdate); //Actualiza el usuario seleccionado
			
			//Usuario usuarioDelete = em.find(Usuario.class, 1);
			//em.remove(usuarioDelete); //Borra el usuario 1

			em.getTransaction().commit();
			
			//Prueba Mauricio
			
			stopEntityManagerFactory();
			System.out.println("Finalizado");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

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
