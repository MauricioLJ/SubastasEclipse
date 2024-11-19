package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import servicio.Servicio;
import servicio.ServicioUsuario;

public class Tester {
	public static void main(String[] args) {

        // Crear una instancia de ServicioUsuario
        ServicioUsuario servicioUsuario = new ServicioUsuario();

        // Crear un nuevo usuario (Create)
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Felipe");
        nuevoUsuario.setCorreo("Felipe@gmail.com");
        nuevoUsuario.setApellidos("Mendez");
        nuevoUsuario.setContrasena("1234");
        nuevoUsuario.setPais("Costa rica");
        nuevoUsuario.setFotoPerfil("hola");
        
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
        servicioUsuario.stopEntityManagerFactory();
		

	}
	
	
}
