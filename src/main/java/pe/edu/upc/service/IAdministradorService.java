package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Administrador;

public interface IAdministradorService {

	public void insertar(Administrador administrador);

	public void modificar(Administrador administrador);

	Optional<Administrador> listarId(int idPersona);

	List<Administrador> listar();

	List<Administrador> buscarDni(String dniPersona);

	List<Administrador> buscarName(String namePersona);

	
	List<Administrador> buscarNombreCaso(String namePersona);
}
