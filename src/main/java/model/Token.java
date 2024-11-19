package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Token
 *
 */
@Entity
public class Token implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Token() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String token;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
   
}
