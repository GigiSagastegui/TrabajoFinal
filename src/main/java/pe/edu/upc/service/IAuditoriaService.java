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
	
	
	List<Auditoria> buscarResultado(String resultado);
	List<Auditoria> buscarPrioridad(String prioridad);
	List<Auditoria> buscarDescripcion(String descripcionAuditoria);
	List<Auditoria> buscarNombreCaso(String descripcionAuditoria);
}
