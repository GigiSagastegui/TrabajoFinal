package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Auditoria;

public interface IAuditoriaService {

	public void insertar(Auditoria auditoria);
	public void modificar(Auditoria auditoria);
	Optional<Auditoria> listarId(int idAuditoria);
	List<Auditoria> listar();
	List<Auditoria> buscarEstado(String estado);
	
}
