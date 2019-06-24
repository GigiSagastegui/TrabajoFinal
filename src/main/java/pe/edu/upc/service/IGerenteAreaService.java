package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Gerente;


public interface IGerenteAreaService {

	public boolean insertar(Gerente gerente);

	public void modificar(Gerente gerente);

	

	Optional<Gerente> listarId(int idPersona);

	List<Gerente> listar();

	List<Gerente> buscarDni(String dniPersona);

	List<Gerente> buscarName(String namePersona);
	
}
