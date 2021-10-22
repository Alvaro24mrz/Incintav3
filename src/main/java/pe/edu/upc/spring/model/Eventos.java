package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name="fechaInicio", length=60, nullable=false)
	private String hInicio;
	
	@Column(name="fechaFin", length=60, nullable=false)
	private String hFin;
	
	
	@Column(name="todoElDia", length=60, nullable=false)
	private String boolTodoDia;
	
	@Column(name="Repeticion", length=60, nullable=false)
	private int qRepeticion;

	public Eventos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Eventos(int idEventos, Usuario usuario, String tTitulo, String tDescripcion, String hInicio, String hFin,
			String boolTodoDia, int qRepeticion) {
		super();
		this.idEventos = idEventos;
		this.usuario = usuario;
		this.tTitulo = tTitulo;
		this.tDescripcion = tDescripcion;
		this.hInicio = hInicio;
		this.hFin = hFin;
		this.boolTodoDia = boolTodoDia;
		this.qRepeticion = qRepeticion;
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

	public String gethInicio() {
		return hInicio;
	}

	public void sethInicio(String hInicio) {
		this.hInicio = hInicio;
	}

	public String gethFin() {
		return hFin;
	}

	public void sethFin(String hFin) {
		this.hFin = hFin;
	}

	public String getBoolTodoDia() {
		return boolTodoDia;
	}

	public void setBoolTodoDia(String boolTodoDia) {
		this.boolTodoDia = boolTodoDia;
	}

	public int getqRepeticion() {
		return qRepeticion;
	}

	public void setqRepeticion(int qRepeticion) {
		this.qRepeticion = qRepeticion;
	}



	
}
