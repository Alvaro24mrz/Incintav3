package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MetodoDePago")
public class MetodoDePago implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iDMetodoPago;
	
	@Column(name="nombreMetodoPago", nullable=false, length=30)
	private String nombreMetodoPago;

	public MetodoDePago() {
		super();
	}

	public MetodoDePago(int iDMetodoPago, String nombreMetodoPago) {
		super();
		this.iDMetodoPago = iDMetodoPago;
		this.nombreMetodoPago = nombreMetodoPago;
	}

	public int getiDMetodoPago() {
		return iDMetodoPago;
	}

	public void setiDMetodoPago(int iDMetodoPago) {
		this.iDMetodoPago = iDMetodoPago;
	}

	public String getNombreMetodoPago() {
		return nombreMetodoPago;
	}

	public void setNombreMetodoPago(String nombreMetodoPago) {
		this.nombreMetodoPago = nombreMetodoPago;
	}
}
