package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Tarea;
import pe.edu.upc.repository.TareaRepository;
import pe.edu.upc.service.ITareaService;

@Service
public class TareaServiceImpl implements ITareaService {

	@Autowired
	private TareaRepository tR;

	@Override
	@Transactional
	public void insertar(Tarea tarea) {
		tR.save(tarea);

	}

	@Override
	@Transactional
	public void modificar(Tarea tarea) {
		tR.save(tarea);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarea> listarId(int idTarea) {

		return tR.findById(idTarea);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarea> listar() {

		return tR.findAll();
	}

}
