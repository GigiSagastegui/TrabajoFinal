package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "programas")
public class Programa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrograma;

	@NotBlank(message = "No puede estar en blanco")
	@NotEmpty(message = "Ingresa la descripcion del programa de auditoria")
	@Column(name = "nombrePrograma", nullable = false, length = 45)
	private String nombrePrograma;

	@NotBlank(message = "No puede estar en blanco")
	@NotEmpty(message = "Ingresa los objetivos")
	@Column(name = "objetivo", nullable = false, length = 3000)
	private String objetivo;

	@NotBlank(message = "No puede estar en blanco")
	@NotEmpty(message = "Ingresa los objetivos")
	@Column(name = "alcance", nullable = false, length = 3000)
	private String alcance;

	public Programa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Programa(int idPrograma, String nombrePrograma, String objetivo, String alcance) {
		super();
		this.idPrograma = idPrograma;
		this.nombrePrograma = nombrePrograma;
		this.objetivo = objetivo;
		this.alcance = alcance;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPrograma;
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
		Programa other = (Programa) obj;
		if (idPrograma != other.idPrograma)
			return false;
		return true;
	}
	
	

}
