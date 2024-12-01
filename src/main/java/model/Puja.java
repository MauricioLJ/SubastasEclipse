package model;

import java.io.Serializable;
import java.lang.Long;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Puja
 *
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Puja.listarTodas", query = "SELECT p FROM Puja p"),
	@NamedQuery(name = "Puja.porSubasta", query = "SELECT p FROM Puja p WHERE p.subasta.idSubasta = :idSubasta") 
	
})
public class Puja implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPuja;
	private double monto;

	@Column(name = "fechaPuja")
	private Timestamp fechaPuja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idSubasta")
	private Subasta subasta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	private static final long serialVersionUID = 1L;

	public Puja() {
		super();
		this.fechaPuja = new Timestamp(System.currentTimeMillis());
	}

	public Integer getIdPuja() {
		return this.idPuja;
	}

	public void setIdPuja(Integer idPuja) {
		this.idPuja = idPuja;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Subasta getSubasta() {
		return this.subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Timestamp getFechaPuja() {
        return fechaPuja;
    }

    public void setFechaPuja(Timestamp fechaPuja) {
        this.fechaPuja = fechaPuja;
    }

}
