package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorColumn(name = "personas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "namePersona", length = 60, nullable = false)
	private String namePersona;

	@Size(min = 9, max = 9)
	@Column(name = "TelPersona", length = 9, nullable = false)
	private String TelPersona;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "DireccionPersona", length = 60, nullable = false)
	private String DireccionPersona;

	@Size(min = 8, max = 8)
	@Column(name = "dniPersona", length = 8, nullable = false)
	private String dniPersona;

	@NotNull
	@Past(message = "No puedes seleccionar un dia que todavia no existe")
	@Temporal(TemporalType.DATE)
	@Column(name = "birthDatePersona")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDatePersona;

	@NotEmpty(message = "No puede estar vacío")
	@NotBlank(message = "No puede estar en blanco")
	@Email
	@Column(name = "emailPersona", length = 60, nullable = false)
	private String emailPersona;

	private String foto;

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String namePersona, String telPersona, String direccionPersona, String dniPersona,
			Date birthDatePersona, String emailPersona, String foto) {
		super();
		this.idPersona = idPersona;
		this.namePersona = namePersona;
		TelPersona = telPersona;
		DireccionPersona = direccionPersona;
		this.dniPersona = dniPersona;
		this.birthDatePersona = birthDatePersona;
		this.emailPersona = emailPersona;
		this.foto = foto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNamePersona() {
		return namePersona;
	}

	public void setNamePersona(String namePersona) {
		this.namePersona = namePersona;
	}

	public String getTelPersona() {
		return TelPersona;
	}

	public void setTelPersona(String telPersona) {
		TelPersona = telPersona;
	}

	public String getDireccionPersona() {
		return DireccionPersona;
	}

	public void setDireccionPersona(String direccionPersona) {
		DireccionPersona = direccionPersona;
	}

	public String getDniPersona() {
		return dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}

	public Date getBirthDatePersona() {
		return birthDatePersona;
	}

	public void setBirthDatePersona(Date birthDatePersona) {
		this.birthDatePersona = birthDatePersona;
	}

	public String getEmailPersona() {
		return emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersona;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (idPersona != other.idPersona)
			return false;
		return true;
	}

}
