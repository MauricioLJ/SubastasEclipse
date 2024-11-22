package servicio;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.TypedQuery;

import model.Token;

public class ServicioToken extends Servicio {

	// Método para generar y guardar el token en la base de datos
    public String generarToken(String email) {
        String token = generarTokenAleatorio();
        guardarTokenEnBD(email, token);
        return token;
    }

    // Método para generar un token aleatorio (puedes hacer esto más seguro)
    private String generarTokenAleatorio() {
        // Generar un token de ejemplo (puedes hacer esto más seguro)
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    // Método para guardar el token en la base de datos utilizando JPA
    private void guardarTokenEnBD(String email, String token) {
        Token tokenEntity = new Token();
        tokenEntity.setEmail(email);
        tokenEntity.setToken(token);
        tokenEntity.setExpirationTime(LocalDateTime.now().plusMinutes(30)); 

        try {
            startTransaction(); 
            em.persist(tokenEntity); 
            commitTransaction();
        } catch (RuntimeException e) {
            rollbackTransaction(); 
            throw e; 
        }
    }

    // Método para obtener el email asociado al token
    public String obtenerEmailPorToken(String token) {
        try {
            TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.token = :token AND t.expirationTime > :now", Token.class);
            query.setParameter("token", token);
            query.setParameter("now", LocalDateTime.now());

            Token result = query.getSingleResult();
            return result != null ? result.getEmail() : null;
        } catch (Exception e) {
            return null; // Token no encontrado o expirado
        }
    }

    // Método para eliminar el token después de su uso
    public void eliminarToken(String token) {
        try {
            startTransaction(); // Iniciar la transacción
            Token tokenEntity = em.createQuery("SELECT t FROM Token t WHERE t.token = :token", Token.class)
                    .setParameter("token", token)
                    .getSingleResult();
            if (tokenEntity != null) {
                em.remove(tokenEntity); // Eliminar el token
            }
            commitTransaction(); // Confirmar la transacción
        } catch (RuntimeException e) {
            rollbackTransaction(); // Deshacer en caso de error
            throw e; // Re-lanzar la excepción si algo falla
        }
    }

    // Método para actualizar la contraseña (en este ejemplo se omite la lógica de actualización de la base de datos)
    public void actualizarClave(String token, String nuevaClave) {
        String email = obtenerEmailPorToken(token);
        if (email != null) {
            // Aquí actualizas la contraseña de tu usuario, lo cual depende de cómo gestiones la entidad del usuario
            System.out.println("Actualizando contraseña para: " + email);
        }
    }
	
}
