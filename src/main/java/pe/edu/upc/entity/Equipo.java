package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipo;

	@NotEmpty(message = "Ingresa el cargo")
	@NotBlank(message = "No puede estar en blanco")
	@Column(name = "cargo", nullable = false, length = 45)
	private String cargo;

	@ManyToOne
	@JoinColumn(name = "idPersona")
	private Auditor auditor;

	@ManyToOne
	@JoinColumn(name = "idAuditoria")
	private Auditoria auditoria;

	public Equipo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Equipo(int idEquipo, String cargo, Auditor auditor, Auditoria auditoria) {
		super();
		this.idEquipo = idEquipo;
		this.cargo = cargo;
		this.auditor = auditor;
		this.auditoria = auditoria;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEquipo;
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
		Equipo other = (Equipo) obj;
		if (idEquipo != other.idEquipo)
			return false;
		return true;
	}

}
