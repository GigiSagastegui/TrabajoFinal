package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Proceso;

public interface IProcesoService {
	public void insertar(Proceso proceso);

	public void modificar(Proceso proceso);

	Optional<Proceso> listarId(int idProceso);

	List<Proceso> listar();

	List<Proceso> buscarArea(String nombreArea);
	
	List<Proceso> buscarNombre(String nombreProceso);
	
	List<Proceso> buscarNombreCaso(String namePersona);
}
