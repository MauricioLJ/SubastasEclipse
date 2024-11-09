package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Subasta
 *
 */
@Entity

public class Subasta implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSubasta;
	private String nombre;
	private String descripcion;
	private long precioInicial;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String estadoSubasta;
	private String imagen;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
	private Usuario propietario;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Puja pujaGanadora;
	
	@OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Puja> pujas = new HashSet<>();
	
	@ManyToMany
    @JoinTable(
        name = "subasta_categoria",
        joinColumns = @JoinColumn(name = "subasta_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
	private Set<Categoria> categorias = new HashSet<>();
	private static final long serialVersionUID = 1L;

	public Subasta() {
		super();
	}   
	public Integer getIdSubasta() {
		return this.idSubasta;
	}

	public void setIdSubasta(Integer idSubasta) {
		this.idSubasta = idSubasta;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public long getPrecioInicial() {
		return this.precioInicial;
	}

	public void setPrecioInicial(long precioInicial) {
		this.precioInicial = precioInicial;
	}   
	public LocalDateTime getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}   
	public LocalDateTime getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}   
	public String getEstadoSubasta() {
		return this.estadoSubasta;
	}

	public void setEstadoSubasta(String estadoSubasta) {
		this.estadoSubasta = estadoSubasta;
	}   
	public Usuario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}   
	public Puja getPujaGanadora() {
		return this.pujaGanadora;
	}

	public void setPujaGanadora(Puja pujaGanadora) {
		this.pujaGanadora = pujaGanadora;
	}   
	public Set<Categoria> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Set<Puja> getPujas() {
		return pujas;
	}
	public void setPujas(Set<Puja> pujas) {
		this.pujas = pujas;
	}
	
	public String getImagen() {
		return this.imagen;
	}

	public void getImagen(String imagen) {
		this.imagen = imagen;
	} 
	
}
