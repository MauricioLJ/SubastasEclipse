package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Interacciones
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = "Interacciones.buscarPorUserSubasta",
        query = "SELECT i FROM Interacciones i WHERE i.usuario.idUsuario = :idUsuario AND i.subasta.idSubasta = :idSubasta AND i.tipoInteraccion = :tipo"
    )
})

public class Interacciones implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInteraccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSubasta", nullable = false)
    private Subasta subasta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoInteraccion", nullable = false)
    private TipoInteraccion tipoInteraccion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaInteraccion", nullable = false)
    private Date fechaInteraccion;
	
	private static final long serialVersionUID = 1L;

	public Interacciones() {
		super();
	}

    public Integer getIdInteraccion() {
        return idInteraccion;
    }

    public void setIdInteraccion(Integer idInteraccion) {
        this.idInteraccion = idInteraccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public TipoInteraccion getTipoInteraccion() {
        return tipoInteraccion;
    }

    public void setTipoInteraccion(TipoInteraccion tipoInteraccion) {
        this.tipoInteraccion = tipoInteraccion;
    }

    public Date getFechaInteraccion() {
        return fechaInteraccion;
    }

    public void setFechaInteraccion(Date fechaInteraccion) {
        this.fechaInteraccion = fechaInteraccion;
    }

    public enum TipoInteraccion {
        VER
    }
   
}