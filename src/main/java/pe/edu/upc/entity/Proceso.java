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
@Table(name = "procesos")
public class Proceso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProceso;
	
	@NotEmpty(message = "Ingresa el nombre del proceso.")
	@Column(name = "nombreProceso", nullable = false, length = 3000)
	private String nombreProceso;

	@NotEmpty(message = "Ingresa la descripcion del proceso")
	@Column(name = "descripcionProceso", nullable = false, length = 3000)
	private String descripcionProceso;

	@ManyToOne
	@JoinColumn(name = "idArea")
	private Area area;
	
	private String foto;

	public Proceso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proceso(int idProceso, String descripcionProceso, Area area, String nombreProceso,
			String foto) {
		super();
		this.idProceso = idProceso;
		this.descripcionProceso = descripcionProceso;
		this.area = area;
		this.nombreProceso=nombreProceso;
		this.foto=foto;
	}

	public int getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}

	public String getDescripcionProceso() {
		return descripcionProceso;
	}

	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProceso;
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
		Proceso other = (Proceso) obj;
		if (idProceso != other.idProceso)
			return false;
		return true;
	}

}
