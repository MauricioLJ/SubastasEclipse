	package model;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import servicio.Servicio;
import servicio.ServicioUsuario;

public class TesterLogin {

    public static void main(String[] args) {
    	// Inicializar la EntityManagerFactory

        // Crear una instancia del servicio
        ServicioUsuario servicioUsuario = new ServicioUsuario();

        // Scanner para capturar la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Proceso de registro
        System.out.println("=== Registro de Usuario ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        // Crear y registrar un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(contrasena);

        servicioUsuario.crearUsuario(nuevoUsuario);
        System.out.println("Usuario registrado exitosamente.");

        // Proceso de inicio de sesión
        System.out.println("\n=== Inicio de Sesión ===");
        System.out.print("Correo: ");
        String loginCorreo = scanner.nextLine();

        System.out.print("Contraseña: ");
        String loginContrasena = scanner.nextLine();

        // Autenticación
        Usuario usuarioLogueado = servicioUsuario.loginUsuario(loginCorreo, loginContrasena);

        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuarioLogueado.getNombre() + "!");
        } else {
            System.out.println("Error: Correo o contraseña incorrectos.");
        }

        // Cerrar el EntityManagerFactory y el scanner
        servicioUsuario.stopEntityManagerFactory();
        scanner.close();
    }
}
