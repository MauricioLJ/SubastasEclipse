package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUsuario;
	private String nombre;
	private String apellidos;
	private String correo;
	private String contrasena;
	private String pais;
	private String fotoPerfil;
	private Double calificacionPromedio = 0.0;
    private Integer cantidadCalificaciones = 0;
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}   
	
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}   
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}   
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public String getPais() {
		return this.pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getFotoPerfil () {
		return this.fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public Double getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(Double calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}

	public Integer getCantidadCalificaciones() {
		return cantidadCalificaciones;
	}

	public void setCantidadCalificaciones(Integer cantidadCalificaciones) {
		this.cantidadCalificaciones = cantidadCalificaciones;
	}

}
