package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="roles")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@Column(name="nombreRol", length=60, nullable=false)
	private String nRol;
	
	

	public Role() {
		super();
	}



	public Role(int idRol, String nRol) {
		super();
		this.idRol = idRol;
		this.nRol = nRol;
	}



	public int getIdRol() {
		return idRol;
	}



	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}



	public String getnRol() {
		return nRol;
	}



	public void setnRol(String nRol) {
		this.nRol = nRol;
	}



	

	

	
	
	
	
}
