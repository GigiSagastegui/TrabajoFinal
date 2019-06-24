package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="auditor")
public class Auditor extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	public Auditor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Auditor(int idPersona, String namePersona, String telPersona, String direccionPersona, String dniPersona,
			java.util.Date birthDatePersona, String emailPersona, String foto) {
		super(idPersona, namePersona, telPersona, direccionPersona, dniPersona, birthDatePersona, emailPersona, foto);
		// TODO Auto-generated constructor stub
	}

	

	
	
	
}
