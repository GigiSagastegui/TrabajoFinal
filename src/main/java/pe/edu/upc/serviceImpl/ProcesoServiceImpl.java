package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Proceso;
import pe.edu.upc.repository.ProcesoRepository;
import pe.edu.upc.service.IProcesoService;

@Service
public class ProcesoServiceImpl implements IProcesoService {
	@Autowired
	private ProcesoRepository prR;

	@Override
	@Transactional
	public void insertar(Proceso proceso) {
		prR.save(proceso);
	}

	@Override
	@Transactional
	public void modificar(Proceso proceso) {
		prR.save(proceso);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Proceso> listarId(int idProceso) {
		return prR.findById(idProceso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Proceso> listar() {
		return prR.findAll();
	}

	@Override
	public List<Proceso> buscarArea(String nombreArea) {
		return prR.buscarArea(nombreArea);
	}

}