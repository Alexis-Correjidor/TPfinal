package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "testimonios")
public class Testimonio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testi_id")
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_identificador")
	private Usuario usuario;
	
	@Column(name = "testi_fechaComent")
	private LocalDate fechaComentario;
	
	
	@NotEmpty(message="Este campo no debe estar vacio")
	@Column(name = "testi_comentario")
	private String comentario;

	private String nombre;
	
	@Column(name = "testi_estado")
	private boolean estado;
	
	public Testimonio() {
		// TODO Auto-generated constructor stub
	}

	public Testimonio(Long id, Usuario usuario, LocalDate fechaComentario, String comentario, boolean estado, String nombre) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fechaComentario = fechaComentario;
		this.comentario = comentario;
		this.estado = estado;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		
		this.usuario= usuario;
	}

	public LocalDate getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(LocalDate fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}

