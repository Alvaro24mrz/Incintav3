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
@Table(name="Eventos")
public class Eventos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEventos;
	
	@ManyToOne 
	@JoinColumn(name="usuarioID", nullable=false)
	private Usuario usuario;
	
	@Column(name="nombreTitulo", length=60, nullable=false)
	private String tTitulo;
	
	@Column(name="nombreDescripcion", length=60, nullable=false)
	private String tDescripcion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaEv")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEvento;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horaInicio")
	@DateTimeFormat(pattern = "HH:mm")
	private Date hInicio;
	
	@Temporal(TemporalType.TIME)
	@Column(name="horaFin")
	@DateTimeFormat(pattern = "HH:mm")
	private Date hFin;

	public Eventos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Eventos(int idEventos, Usuario usuario, String tTitulo, String tDescripcion, Date fechaEvento, Date hInicio,
			Date hFin) {
		super();
		this.idEventos = idEventos;
		this.usuario = usuario;
		this.tTitulo = tTitulo;
		this.tDescripcion = tDescripcion;
		this.fechaEvento = fechaEvento;
		this.hInicio = hInicio;
		this.hFin = hFin;
	}

	public int getIdEventos() {
		return idEventos;
	}

	public void setIdEventos(int idEventos) {
		this.idEventos = idEventos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String gettTitulo() {
		return tTitulo;
	}

	public void settTitulo(String tTitulo) {
		this.tTitulo = tTitulo;
	}

	public String gettDescripcion() {
		return tDescripcion;
	}

	public void settDescripcion(String tDescripcion) {
		this.tDescripcion = tDescripcion;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public Date gethInicio() {
		return hInicio;
	}

	public void sethInicio(Date hInicio) {
		this.hInicio = hInicio;
	}

	public Date gethFin() {
		return hFin;
	}

	public void sethFin(Date hFin) {
		this.hFin = hFin;
	}

	

	
}
