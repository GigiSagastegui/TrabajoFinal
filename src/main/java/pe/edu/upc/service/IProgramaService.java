package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Programa;

public interface IProgramaService {
	
	public void insertar(Programa programa);

	public void modificar(Programa programa);

	Optional<Programa> listarId(int idPrograma);

	List<Programa> listar();

}
