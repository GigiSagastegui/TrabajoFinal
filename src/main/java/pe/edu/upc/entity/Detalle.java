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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	@NotEmpty(message = "Ingresa el resultado de la auditoria")
	@Column(name = "resultadoAuditoria", nullable = false, length = 45)
	private String resultadoAuditoria;

	@ManyToOne
	@JoinColumn(name = "descripcionProceso")
	private Proceso proceso;

	@ManyToOne
	@JoinColumn(name = "idAuditoria")
	private Auditoria auditoria;

	public Detalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Detalle(int idDetalle, String resultadoAuditoria, Proceso proceso, Auditoria auditoria) {
		super();
		this.idDetalle = idDetalle;
		this.resultadoAuditoria = resultadoAuditoria;
		this.proceso = proceso;
		this.auditoria = auditoria;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getResultadoAuditoria() {
		return resultadoAuditoria;
	}

	public void setResultadoAuditoria(String resultadoAuditoria) {
		this.resultadoAuditoria = resultadoAuditoria;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
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
		result = prime * result + idDetalle;
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
		Detalle other = (Detalle) obj;
		if (idDetalle != other.idDetalle)
			return false;
		return true;
	}

}
