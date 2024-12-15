package model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notificacion
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Notificacion.listarTodas", query = "SELECT n FROM Notificacion n"),
		@NamedQuery(name = "Notificacion.porUsuario", query = "SELECT n FROM Notificacion n WHERE n.usuario.idUsuario = :idUsuario"),
		@NamedQuery(name = "Notificacion.noLeidas", query = "SELECT n FROM Notificacion n WHERE n.usuario.idUsuario = :idUsuario AND n.leida = false")

})
public class Notificacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNotificacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	private String tipoNotificacion; // e.g., "PUJA", "SUBASTA", "MENSAJE"
	private String mensaje;
	private Date fechaCreacion;

	// New field to track if notification has been read
	private boolean leida = false;

	// Optional: Additional context fields for bid notifications
	private Integer subastaId;
	private Double montoPuja;
	private String subastaNombre;

	private static final long serialVersionUID = 1L;

	public Notificacion() {
		super();
		this.fechaCreacion = new Date();
	}

	public void marcarComoLeida() {
		this.leida = true;
	}

	// Existing getters and setters, plus new ones for added fields
	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}

	public Integer getSubastaId() {
		return subastaId;
	}

	public void setSubastaId(Integer subastaId) {
		this.subastaId = subastaId;
	}

	public Double getMontoPuja() {
		return montoPuja;
	}

	public void setMontoPuja(Double montoPuja) {
		this.montoPuja = montoPuja;
	}

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public String getSubastaNombre() {
        return subastaNombre;
    }

    public void setSubastaNombre(String subastaNombre) {
        this.subastaNombre = subastaNombre;
    }
}