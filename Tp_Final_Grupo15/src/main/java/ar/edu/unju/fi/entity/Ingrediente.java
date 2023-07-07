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

@Component
@Entity
@Table(name = "ingredientes")
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ing_id")
	private Long id;
	@Column(name = "ing_nombre")
	private String nombre;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Receta> recetas;
	
	public Ingrediente() {
		// TODO Auto-generated constructor stub
	}

	public Ingrediente(Long id, String nombre, List<Receta> recetas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.recetas = recetas;
	}


	public List<Receta> getRecetas() {
		return recetas;
	}


	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
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

}
