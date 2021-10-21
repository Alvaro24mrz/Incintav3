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
@Table(name="PreguntasGestante")
public class PreguntasGestante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPreguntasGestante;
	
	//el fk
	@ManyToOne 
	@JoinColumn(name="usuarioID", nullable=false)
	private Usuario usuario;
	
	@Column(name="nombreTitulo", length=60, nullable=false)
	private String nTitulo;
	
	@Column(name="nombrePregunta", length=60, nullable=false)
	private String tPregunta;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaPregunta")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;

	public PreguntasGestante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PreguntasGestante(int idPreguntasGestante, Usuario usuario, String nTitulo, String tPregunta, Date fecha) {
		super();
		this.idPreguntasGestante = idPreguntasGestante;
		this.usuario = usuario;
		this.nTitulo = nTitulo;
		this.tPregunta = tPregunta;
		this.fecha = fecha;
	}

	public int getIdPreguntasGestante() {
		return idPreguntasGestante;
	}

	public void setIdPreguntasGestante(int idPreguntasGestante) {
		this.idPreguntasGestante = idPreguntasGestante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getnTitulo() {
		return nTitulo;
	}

	public void setnTitulo(String nTitulo) {
		this.nTitulo = nTitulo;
	}

	public String gettPregunta() {
		return tPregunta;
	}

	public void settPregunta(String tPregunta) {
		this.tPregunta = tPregunta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}
