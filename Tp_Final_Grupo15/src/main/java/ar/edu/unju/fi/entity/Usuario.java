package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
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
	@Size(min = 3, max = 30, message = "Ingrese un nombre entre 3 y 30 caracteres")
	@Pattern(regexp = "[a-z A-Z]+", message = "El nombre debe contener solo letras")
	private String nombre;

	@Column(name = "usuario_apellido")
	@Size(min = 3, max = 30, message = "Ingrese un apellido entre 3 y 30 caracteres")
	@Pattern(regexp = "[a-z A-Z]+", message = "El apellido debe contener solo letras")
	private String apellido;
	
	@Column(name = "usuario_email")
	@NotEmpty(message="Debes introducir un email")
	@Email(message="Asegurese de ingresar un email con formato válido")
	private String email;
	
	@Column(name = "usuario_telefono")
	@Pattern(regexp = "[0-9]+", message = "Ingrese un teléfono usando solo números")
	@Size(min = 7, message = "Ingrese un teléfono de mínimo 7 dígitos")
	private String telefono;
	
	@Column(name = "usuario_estatura")
	@Positive(message="La estatura debe ser un valor positivo")
	@DecimalMin(value = "0.40", message = "La estatura puede ser como mínimo de 0.40")
	@Max(value=3, message="La estatura puede ser como máximo de 3 metros")
	private float estatura;
	
	@Column(name = "usuario_sexo")
	@NotBlank(message="Debes introducir su sexo")
	private String sexo;
	
	@Column(name = "usuario_fecha_nacimiento")
	@NotNull(message = "Debes ingresar una fecha")
	@Past(message = "Asegurere de haber ingresado una fecha pasada")
	private LocalDate fechaNacimiento;

	public Usuario() {
		this.setEstado(true);
	}

	public Usuario(long identificador, boolean estado,
			String nombre,
			String apellido,
			String email,
			String telefono,
			float estatura,
			String sexo,
			LocalDate fechaNacimiento) {
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
