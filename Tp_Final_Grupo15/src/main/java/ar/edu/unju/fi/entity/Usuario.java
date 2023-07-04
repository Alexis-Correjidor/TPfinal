package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
    @Column(name = "usuario_identificador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long identificador;
	
	@Column(name = "usuario_estado", columnDefinition = "boolean default true")
    private boolean estado;
	
	@Column(name = "usuario_nombre")
	@NotEmpty(message = "Debes introducir un nombre")
	@Size(min = 3, max = 30, message = "El nombre solo puede contener entre 3 y 30 caracteres")
	@Pattern(regexp = "[a-z A-Z]+", message = "Debe contener solo letras")
	private String nombre;

	@Column(name = "usuario_apellido")
	@NotEmpty(message = "Debes introducir un apellido")
	@Size(min = 4, max = 30, message = "El apellido solo puede contener entre 4 y 30 caracteres")
	@Pattern(regexp = "[a-z A-Z]+", message = "Debe contener solo letras")
	private String apellido;
	
	@Column(name = "usuario_email")
	@NotEmpty(message="Debes introducir un email")
	@Email(message="Asegurese de ingresar un email con formato válido")
	private String email;
	
	@Column(name = "usuario_telefono")
	@NotBlank(message="Debes introducir un teléfono")
	@Pattern(regexp = "[0-9]+", message = "El telefono debe contener solo números")
	private String telefono;
	
	@Column(name = "usuario_estatura")
	@NotEmpty(message="Debes introducir una estatura")
	@Positive(message="La estatura debe ser un valor positivo")
	private float estatura;
	
	@Column(name = "usuario_sexo")
	@NotBlank(message="Debes introducir su sexo")
	private String sexo;
	
	@Column(name = "usuario_fecha_nacimiento")
	@NotNull(message = "Debes ingresar una fecha")
	@Past(message = "Asegurere de haber ingresado una fecha pasada")
	private LocalDate fechaNacimiento;

	public Usuario() {
		
	}

	public Usuario(long identificador, boolean estado,
			String nombre,
			String apellido,
			String email,
			String telefono,
			float estatura,
			String sexo,
			LocalDate fechaNacimiento) {
		super();
		this.identificador = identificador;
		this.estado = estado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.estatura = estatura;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(long identificador) {
		this.identificador = identificador;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
}
