package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Tarea;

public interface ITareaService {
	
	public void insertar(Tarea tarea);

	public void modificar(Tarea tarea);
	
	Optional<Tarea> listarId(int idTarea);

	List<Tarea> listar();

}
