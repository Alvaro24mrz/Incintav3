package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TipoIdentificacion")
public class TipoIdentificacion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoIdentificacion;

	
	@Column(name="nombreIdentificacion", length=60, nullable=false)
	private String nIdentificacion;


	public TipoIdentificacion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TipoIdentificacion(int idTipoIdentificacion, String nIdentificacion) {
		super();
		this.idTipoIdentificacion = idTipoIdentificacion;
		this.nIdentificacion = nIdentificacion;
	}


	public int getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}


	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}


	public String getnIdentificacion() {
		return nIdentificacion;
	}


	public void setnIdentificacion(String nIdentificacion) {
		this.nIdentificacion = nIdentificacion;
	}
	


}
