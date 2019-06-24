package pe.edu.upc.entity;

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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "auditorias")
public class Auditoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAuditoria;

	@NotEmpty(message = "Ingresa la descripcion de la auditoria")
	@Column(name = "descripcionAuditoria", nullable = false, length = 45)
	private String descripcionAuditoria;

	@ManyToOne
	@JoinColumn(name = "idProceso")
	private Proceso proceso;

	@NotNull(message = "La fecha es obligatoria")
	@Past(message = "La fecha debe estar en el pasado.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaInicioAuditoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicioAuditoria;

	@NotNull(message = "La fecha es obligatoria")
	@Future(message = "La fecha debe estar en el futuro.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaFinAuditoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFinAuditoria;

	@NotEmpty(message = "Ingresa el estado")
	@Column(name = "estado", nullable = false, length = 45)
	private String estado;

	@NotEmpty(message = "Ingresa la prioridad")
	@Column(name = "prioridad", nullable = false, length = 45)
	private String prioridad;

	@NotEmpty(message = "Ingresa el resultado")
	@Column(name = "resultado", nullable = false, length = 45)
	private String resultado;

	public Auditoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Auditoria(int idAuditoria, String descripcionAuditoria, Proceso proceso, Date fechaInicioAuditoria,
			Date fechaFinAuditoria, String estado,

			String prioridad, String resultado) {
		super();
		this.idAuditoria = idAuditoria;
		this.descripcionAuditoria = descripcionAuditoria;
		this.proceso = proceso;
		this.fechaInicioAuditoria = fechaInicioAuditoria;
		this.fechaFinAuditoria = fechaFinAuditoria;
		this.estado = estado;
		this.prioridad = prioridad;
		this.resultado = resultado;
	}

	public int getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public String getDescripcionAuditoria() {
		return descripcionAuditoria;
	}

	public void setDescripcionAuditoria(String descripcionAuditoria) {
		this.descripcionAuditoria = descripcionAuditoria;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Date getFechaInicioAuditoria() {
		return fechaInicioAuditoria;
	}

	public void setFechaInicioAuditoria(Date fechaInicioAuditoria) {
		this.fechaInicioAuditoria = fechaInicioAuditoria;
	}

	public Date getFechaFinAuditoria() {
		return fechaFinAuditoria;
	}

	public void setFechaFinAuditoria(Date fechaFinAuditoria) {
		this.fechaFinAuditoria = fechaFinAuditoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAuditoria;
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
		Auditoria other = (Auditoria) obj;
		if (idAuditoria != other.idAuditoria)
			return false;
		return true;
	}

}
