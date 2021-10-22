package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Unidad")
public class Unidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUnidad;
	
	@Column(name="nombreUnidad", length=60, nullable=false)
	private String nUnidad;
	
	

	public Unidad() {
		super();
	}



	public Unidad(int idUnidad, String nUnidad) {
		super();
		this.idUnidad = idUnidad;
		this.nUnidad = nUnidad;
	}



	public int getIdUnidad() {
		return idUnidad;
	}



	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}



	public String getnUnidad() {
		return nUnidad;
	}



	public void setnUnidad(String nUnidad) {
		this.nUnidad = nUnidad;
	}


	

	
	
	
	
}
