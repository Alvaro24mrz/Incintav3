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
@Table(name="Parametro")
public class Parametro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idParametro;
	
	@Column(name="nombreParametro", length=60, nullable=false)
	private String nParametro;
	
	
	@ManyToOne
	@JoinColumn(name="iDUnidad", nullable=false)
	private Unidad unidad;
	 
	

	public Parametro() {
		super();
	}



	public Parametro(int idParametro, String nParametro, Unidad unidad) {
		super();
		this.idParametro = idParametro;
		this.nParametro = nParametro;
		this.unidad = unidad;
	}



	public int getIdParametro() {
		return idParametro;
	}



	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}



	public String getnParametro() {
		return nParametro;
	}



	public void setnParametro(String nParametro) {
		this.nParametro = nParametro;
	}



	public Unidad getUnidad() {
		return unidad;
	}



	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}


	

	
	
	
	
}
