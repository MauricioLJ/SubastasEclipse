
package servicio;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class ServicioEmail {
	
	private String host = "smtp.gmail.com";
    private int port = 587;
    private String username = "mauriciolj128@gmail.com";
    private String password = "wpzh hpvo xcdk pvrb";

    public void enviarEnlaceRecuperacion(String destinatario, String enlace) {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject("Recuperación de Contraseña");
            msg.setText("Haz clic en el siguiente enlace para cambiar tu contraseña: " + enlace);

            Transport.send(msg);
            System.out.println("Correo enviado exitosamente a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error al enviar correo: " + e.getMessage());
        }
    }
    
}

