package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name="Registro")
public class Registro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRegistro;
	
	
	@ManyToOne
	@JoinColumn(name="idUsuario", nullable=false)
	private Usuario usuario;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha", nullable=false)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaRegistro;
	
	@Column(name="anotaciones", nullable=true)
	private String tAnotaciones; 
	
	
	
	@ManyToOne
	@JoinColumn(name="idParametro", nullable=false)
	private Parametro parametro;
	 
	
	@Column(name="valor", nullable=false)
	private int numValor;
	
	
	
	

	public Registro() {
		super();
	}





	public Registro(int idRegistro, Usuario usuario, Date fechaRegistro, String tAnotaciones, Parametro parametro,
			int numValor) {
		super();
		this.idRegistro = idRegistro;
		this.usuario = usuario;
		this.fechaRegistro = fechaRegistro;
		this.tAnotaciones = tAnotaciones;
		this.parametro = parametro;
		this.numValor = numValor;
	}





	public int getIdRegistro() {
		return idRegistro;
	}





	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}





	public Usuario getUsuario() {
		return usuario;
	}





	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}





	public Date getFechaRegistro() {
		return fechaRegistro;
	}





	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}





	public String gettAnotaciones() {
		return tAnotaciones;
	}





	public void settAnotaciones(String tAnotaciones) {
		this.tAnotaciones = tAnotaciones;
	}





	public Parametro getParametro() {
		return parametro;
	}





	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}





	public int getNumValor() {
		return numValor;
	}





	public void setNumValor(int numValor) {
		this.numValor = numValor;
	}





	


	


	

	
	
	
	
}
