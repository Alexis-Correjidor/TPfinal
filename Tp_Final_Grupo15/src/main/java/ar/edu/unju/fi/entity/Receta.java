package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
@Component
@Entity
@Table(name="recetas")
public class Receta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rec_id")
	private Long id;
	@Column(name = "rec_estado")
	private boolean estado;
	@NotBlank(message = "Debe ingresar nombre de receta")
	@Column(name = "rec_nombre")
	private String nombre;
	@NotEmpty(message = "Debe seleccionar una categoria")
	@Column(name = "rec_categoria")
	private String categoria;
	@NotEmpty(message = "Debe seleccionar los ingredientes")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ingrediente> ingredientes;
	@NotBlank(message = "Debe ingresar preparacion de receta")
	@Column(name = "rec_preparacion")
	private String preparacion;
	@Column(name = "rec_imagen")
	private String imagen;
	
	
	public Receta() {
		// TODO Auto-generated constructor stub
	}

	public Receta(Long id, String nombre, String categoria, List<Ingrediente> ingredientes, String preparacion,
			String imagen, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingredientes = ingredientes;
		this.preparacion = preparacion;
		this.imagen = imagen;
		this.estado = estado;
	}

	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
