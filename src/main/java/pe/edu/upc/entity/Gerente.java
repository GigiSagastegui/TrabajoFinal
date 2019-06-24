package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="gerente")
public class Gerente extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	public Gerente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gerente(int idPersona, String namePersona, String telPersona, String direccionPersona, String dniPersona,
			Date birthDatePersona, String emailPersona, String foto, String estadoUsuario, String tipoUsuario,
			String userUsuario, String passwordUsuario) {
		super(idPersona, namePersona, telPersona, direccionPersona, dniPersona, birthDatePersona, emailPersona, foto,
				estadoUsuario, tipoUsuario, userUsuario, passwordUsuario);
		// TODO Auto-generated constructor stub
	}

	

	
	

}
