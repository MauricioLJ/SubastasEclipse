package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Categoria
 *
 */
@Entity

public class Categoria implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCategoria;
	private String nombre;
	
	@ManyToMany(mappedBy = "categorias", cascade = CascadeType.ALL)
	private Set<Subasta> subastas = new HashSet<>();
	private static final long serialVersionUID = 1L;

	public Categoria() {
		super();
	}   
	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public Set<Subasta> getSubastas() {
		return this.subastas;
	}

	public void setSubastas(Set<Subasta> subastas) {
		this.subastas = subastas;
	}
   
}
