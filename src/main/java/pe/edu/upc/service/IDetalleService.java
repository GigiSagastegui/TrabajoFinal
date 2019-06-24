package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Detalle;

public interface IDetalleService {

	public void insertar(Detalle detalle);

	public void modificar(Detalle detalle);

	Optional<Detalle> listarId(int idDetalle);

	List<Detalle> listar();
	
	List<Detalle> buscarResultado(String resultadoAuditoria);

}
