package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Programa;
import pe.edu.upc.repository.ProgramaRepository;
import pe.edu.upc.service.IProgramaService;

@Service
public class ProgramaServiceImpl implements IProgramaService{

	@Autowired
	private ProgramaRepository pR;

	@Override
	public void insertar(Programa programa) {
		pR.save(programa);
		
	}

	@Override
	public void modificar(Programa programa) {
		pR.save(programa);
		
	}

	@Override
	public Optional<Programa> listarId(int idPrograma) {
		
		return pR.findById(idPrograma);
	}

	@Override
	public List<Programa> listar() {
		
		return pR.findAll();
	}
	
	

}
