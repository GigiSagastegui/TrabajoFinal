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
@Table(name = "tareas")
public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarea;

	@NotEmpty(message = "Ingresa la descripcion de la tarea")
	@Column(name = "descripcionTarea", nullable = false, length = 45)
	private String descripcionTarea;

	@ManyToOne
	@JoinColumn(name = "idProceso")
	private Proceso proceso;

	public Tarea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarea(int idTarea, String descripcionTarea, Proceso proceso) {
		super();
		this.idTarea = idTarea;
		this.descripcionTarea = descripcionTarea;
		this.proceso = proceso;
	}

	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public String getDescripcionTarea() {
		return descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTarea;
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
		Tarea other = (Tarea) obj;
		if (idTarea != other.idTarea)
			return false;
		return true;
	}

}
