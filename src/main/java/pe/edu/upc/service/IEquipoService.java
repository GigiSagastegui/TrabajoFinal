package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Equipo;

public interface IEquipoService {

	public void insertar(Equipo equipo);

	public void modificar(Equipo equipo);

	Optional<Equipo> listarId(int idEquipo);

	List<Equipo> listar();

	List<Equipo> buscarCargo(String cargo);
	List<Equipo> buscarLikeIgnoreCase(String cargo);
	
	
	List<Equipo> buscarAuditoria(String namePersona);
	
	
	
}
