package ar.edu.unju.fi.entity;

import java.text.DecimalFormat;
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

@Component
@Entity
@Table(name = "indicesMC")
public class IndiceMasaCorporal {

    @Id
    @Column(name = "imc_identificador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long identificador;
    
    @Column(name = "imc_fecha")
    private LocalDate fechaIMC;
    
    @Column(name = "imc_estado", columnDefinition = "boolean default true")
    private boolean estado;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario_identificador")
    private Usuario usuario;
    
    @Column(name = "imc_peso")
    private double peso;
    
    @Column(name = "imc_resultado")
    private String resultado;
    
	public IndiceMasaCorporal() {
		super();
		this.estado=true;
	}
	
	


	public IndiceMasaCorporal(long identificador, LocalDate fechaIMC, boolean estado, Usuario usuario, double peso,
			String resultado) {
		super();
		this.identificador = identificador;
		this.fechaIMC = fechaIMC;
		this.estado = estado;
		this.usuario = usuario;
		this.peso = peso;
		this.resultado = resultado;
	}




	public long getIdentificador() {
		return identificador;
	}



	public void setIdentificador(long identificador) {
		this.identificador = identificador;
	}



	public LocalDate getFechaIMC() {
		return fechaIMC;
	}



	public void setFechaIMC(LocalDate fechaIMC) {
		this.fechaIMC = fechaIMC;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	public Usuario getUsuario() {
		return this.usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getResultado() {
		return resultado;
	}



	public void setResultado(String resultado) {
		this.resultado = resultado;
	}



	public double getPeso() {
		return peso;
	}



	public void setPeso(double peso) {
		this.peso = peso;
	}



	public String calcularImc(float altura,double peso) {
		DecimalFormat df = new DecimalFormat("#.00");
    	double imc;
    	imc=peso/(double)(Math.pow(altura,2));
    	System.out.println(imc);
    	if(imc<18.5)
    		return "Su IMC es "+df.format(imc)+"-Está por debajo de su peso ideal";
    	else
    	{
    		if(imc>=18.5 && imc<=25)
    			return "Su IMC es "+df.format(imc)+"-Está en su peso normal";
    		else
    			return "Su IMC es "+df.format(imc)+"-Tiene sobrepeso";
    	}
    }
}
