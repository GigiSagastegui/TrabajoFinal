package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Informe;

public interface IInformeService {

	public void insertar(Informe informe);

	public void modificar(Informe informe);

	Optional<Informe> listarId(int idInforme);

	List<Informe> listar();

	
	List<Informe> buscarAuditor(String namePersona);
}
