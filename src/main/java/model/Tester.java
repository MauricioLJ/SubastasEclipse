package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import servicio.Servicio;
import servicio.ServicioUsuario;

public class Tester {
	public static void main(String[] args) {
		// Inicializar la EntityManagerFactory
        Servicio.startEntityManagerFactory("Proyecto Subasta"); // Cambia esto al nombre de tu unidad de persistencia

        // Crear una instancia de ServicioUsuario
        ServicioUsuario servicioUsuario = new ServicioUsuario();

        // Crear un nuevo usuario (Create)
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Juan");
        nuevoUsuario.setCorreo("juan@example.com");
        nuevoUsuario.setApellidos("Pepe");
        nuevoUsuario.setContrasena("1111");
        
        servicioUsuario.crearUsuario(nuevoUsuario);
        System.out.println("Usuario creado: " + nuevoUsuario);

        // Leer el usuario (Read)
        Usuario usuarioLeido = servicioUsuario.leerUsuario(nuevoUsuario.getIdUsuario());
        System.out.println("Usuario leído: " + usuarioLeido);

        // Actualizar el usuario (Update)
        //usuarioLeido.setNombre("Juan Pérez");
        //servicioUsuario.actualizarUsuario(usuarioLeido);
        //System.out.println("Usuario actualizado: " + usuarioLeido);

        // Eliminar el usuario (Delete)
        //servicioUsuario.eliminarUsuario(usuarioLeido.getIdUsuario());
        //System.out.println("Usuario eliminado con ID: " + usuarioLeido.getIdUsuario());

        // Cerrar la EntityManagerFactory
        Servicio.stopEntityManagerFactory();
		

	}
	
	
}
