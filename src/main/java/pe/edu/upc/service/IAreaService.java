package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Area;

public interface IAreaService {
	public void insertar(Area area);

	public void modificar(Area area);

	Optional<Area> listarId(int idArea);

	List<Area> listar();
	
	List<Area> buscar(String nombreArea);

	List<Area> buscarNombreCaso(String nombreArea);

}
