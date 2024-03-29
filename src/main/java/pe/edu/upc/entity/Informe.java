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
@Table(name = "informes")
public class Informe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInforme;

	@NotBlank(message = "No puede estar en blanco")
	@NotEmpty(message = "Ingresa la descripcion del informe")
	@Column(name = "descripcionInforme", nullable = false, length = 3000)
	private String descripcionInforme;

	@NotBlank(message = "No puede estar en blanco")
	@NotEmpty(message = "Ingrese el tipo de informe")
	@Column(name = "tipoInforme", nullable = false, length = 3000)
	private String tipoInforme;

	@ManyToOne
	@JoinColumn(name = "idProceso")
	private Proceso proceso;

	@ManyToOne
	@JoinColumn(name = "idPersona")
	private Auditor auditor;

	public Informe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Informe(int idInforme, String descripcionInforme, String tipoInforme, Proceso proceso, Auditor auditor) {
		super();
		this.idInforme = idInforme;
		this.descripcionInforme = descripcionInforme;
		this.tipoInforme = tipoInforme;
		this.proceso = proceso;
		this.auditor = auditor;
	}

	
	
	
	
	public int getIdInforme() {
		return idInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}

	public String getDescripcionInforme() {
		return descripcionInforme;
	}

	public void setDescripcionInforme(String descripcionInforme) {
		this.descripcionInforme = descripcionInforme;
	}

	public String getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idInforme;
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
		Informe other = (Informe) obj;
		if (idInforme != other.idInforme)
			return false;
		return true;
	}

}
