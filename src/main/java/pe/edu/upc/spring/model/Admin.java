package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminID;
	
	@Column(name="nombreAdmin", nullable=false, length=30)
	private String nAdmin;
		
	private String aCorreo;
	
	private String aPassword;
		
	//nuevo
	@Column(name="rolUsuario", nullable=false)
	private String rol;
	
	

	public Admin() {
		super();
	}



	public Admin(int adminID, String nAdmin, String aCorreo, String aPassword, String rol) {
		super();
		this.adminID = adminID;
		this.nAdmin = nAdmin;
		this.aCorreo = aCorreo;
		this.aPassword = aPassword;
		this.rol = rol;
	}



	public int getAdminID() {
		return adminID;
	}



	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}



	public String getnAdmin() {
		return nAdmin;
	}



	public void setnAdmin(String nAdmin) {
		this.nAdmin = nAdmin;
	}



	public String getaCorreo() {
		return aCorreo;
	}



	public void setaCorreo(String aCorreo) {
		this.aCorreo = aCorreo;
	}



	public String getaPassword() {
		return aPassword;
	}



	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	
}