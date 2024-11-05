package model;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Puja
 *
 */
@Entity

public class Puja implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPuja;
	private long monto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSubasta")
	private Subasta subasta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;
	private static final long serialVersionUID = 1L;

	public Puja() {
		super();
	}   
	public Long getIdPuja() {
		return this.idPuja;
	}

	public void setIdPuja(Long idPuja) {
		this.idPuja = idPuja;
	}   
	public long getMonto() {
		return this.monto;
	}

	public void setMonto(long monto) {
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
   
}
