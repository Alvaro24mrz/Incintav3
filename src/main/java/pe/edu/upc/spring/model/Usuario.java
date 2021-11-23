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
@Table(name="Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuarioID;
	
	@Column(name="username", nullable=false, length=30, unique=true)
	private String nUsuario;
	
	@Column(name="nGestante", nullable=false, length=30)
	private String nGestante;
	
	private String uApellido;
	
	private String uCorreo;
	
	private String uPassword;
	
	private String numIdentificacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaNacimiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dNacimiento;
	
	@ManyToOne
	@JoinColumn(name="iDTipoIdentificacion", nullable=false)
	private TipoIdentificacion iDTipoIdentificacion;
	
	@ManyToOne
	@JoinColumn(name="iDPais", nullable=false)
	private Pais iDPais;
	
	@ManyToOne
	@JoinColumn(name="idMetodoPago", nullable=false)
	private MetodoDePago iDMetodoPago;
	
	@Column(name="numeroTargeta", nullable=false)
	private int numMetodoPago;
	
	private int semanaGestacion;
	
	private int admin;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Usuario(int usuarioID, String nUsuario, String nGestante, String uApellido, String uCorreo, String uPassword,
			String numIdentificacion, Date dNacimiento, TipoIdentificacion iDTipoIdentificacion, Pais iDPais,
			MetodoDePago iDMetodoPago, int numMetodoPago, int semanaGestacion, int admin) {
		super();
		this.usuarioID = usuarioID;
		this.nUsuario = nUsuario;
		this.nGestante = nGestante;
		this.uApellido = uApellido;
		this.uCorreo = uCorreo;
		this.uPassword = uPassword;
		this.numIdentificacion = numIdentificacion;
		this.dNacimiento = dNacimiento;
		this.iDTipoIdentificacion = iDTipoIdentificacion;
		this.iDPais = iDPais;
		this.iDMetodoPago = iDMetodoPago;
		this.numMetodoPago = numMetodoPago;
		this.semanaGestacion = semanaGestacion;
		this.admin = admin;
	}



	public int getUsuarioID() {
		return usuarioID;
	}



	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}



	public String getnUsuario() {
		return nUsuario;
	}



	public void setnUsuario(String nUsuario) {
		this.nUsuario = nUsuario;
	}

	public String getnGestante() {
		return nGestante;
	}

	public void setnGestante(String nGestante) {
		this.nGestante = nGestante;
	}


	public String getuApellido() {
		return uApellido;
	}



	public void setuApellido(String uApellido) {
		this.uApellido = uApellido;
	}



	public String getuCorreo() {
		return uCorreo;
	}



	public void setuCorreo(String uCorreo) {
		this.uCorreo = uCorreo;
	}



	public String getuPassword() {
		return uPassword;
	}



	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}



	public String getNumIdentificacion() {
		return numIdentificacion;
	}



	public void setNumIdentificacion(String numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}



	public Date getdNacimiento() {
		return dNacimiento;
	}



	public void setdNacimiento(Date dNacimiento) {
		this.dNacimiento = dNacimiento;
	}



	public TipoIdentificacion getiDTipoIdentificacion() {
		return iDTipoIdentificacion;
	}



	public void setiDTipoIdentificacion(TipoIdentificacion iDTipoIdentificacion) {
		this.iDTipoIdentificacion = iDTipoIdentificacion;
	}



	public Pais getiDPais() {
		return iDPais;
	}



	public void setiDPais(Pais iDPais) {
		this.iDPais = iDPais;
	}



	public MetodoDePago getiDMetodoPago() {
		return iDMetodoPago;
	}



	public void setiDMetodoPago(MetodoDePago iDMetodoPago) {
		this.iDMetodoPago = iDMetodoPago;
	}



	public int getNumMetodoPago() {
		return numMetodoPago;
	}



	public void setNumMetodoPago(int numMetodoPago) {
		this.numMetodoPago = numMetodoPago;
	}



	public int getSemanaGestacion() {
		return semanaGestacion;
	}



	public void setSemanaGestacion(int semanaGestacion) {
		this.semanaGestacion = semanaGestacion;
	}


	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}



	
}