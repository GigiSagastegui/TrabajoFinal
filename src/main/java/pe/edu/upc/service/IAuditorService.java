package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Auditor;

public interface IAuditorService {
	
	public boolean insertar(Auditor auditor);

	public void modificar(Auditor auditor);

	

	Optional<Auditor> listarId(int idPersona);

	List<Auditor> listar();

	List<Auditor> buscarDni(String dniPersona);

	List<Auditor> buscarName(String namePersona);
	
	List<Auditor> buscarNombreCaso(String namePersona);

}
