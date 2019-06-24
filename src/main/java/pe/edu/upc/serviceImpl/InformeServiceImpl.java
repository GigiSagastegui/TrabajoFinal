package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Informe;
import pe.edu.upc.repository.InformeRepository;
import pe.edu.upc.service.IInformeService;

@Service
public class InformeServiceImpl implements IInformeService {

	@Autowired
	private InformeRepository in;

	@Override
	@Transactional
	public void insertar(Informe informe) {
		// TODO Auto-generated method stub
		in.save(informe);
	}

	@Override
	@Transactional
	public void modificar(Informe informe) {
		// TODO Auto-generated method stub
		in.save(informe);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Informe> listarId(int idInforme) {
		return in.findById(idInforme);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Informe> listar() {
		return in.findAll();
	}

	@Override
	public List<Informe> buscarAuditor(String namePersona) {
		return in.buscarAuditor(namePersona);
	}

}
