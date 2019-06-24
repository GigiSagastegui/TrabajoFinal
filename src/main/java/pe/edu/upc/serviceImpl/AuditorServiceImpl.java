package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Auditor;
import pe.edu.upc.repository.AuditorRepository;
import pe.edu.upc.service.IAuditorService;

@Service
public class AuditorServiceImpl implements IAuditorService {

	@Autowired
	private AuditorRepository aR;

	@Override
	@Transactional
	public boolean insertar(Auditor auditor) {
		Auditor objAuditor = aR.save(auditor);
		if (objAuditor == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public void modificar(Auditor auditor) {

		aR.save(auditor);
	}

	@Override
	public Optional<Auditor> listarId(int idPersona) {

		return aR.findById(idPersona);
	}

	@Override
	public List<Auditor> listar() {

		return aR.findAll();
	}

	@Override
	public List<Auditor> buscarDni(String dniPersona) {

		return aR.findByDniPersona(dniPersona);
	}

	@Override
	public List<Auditor> buscarName(String namePersona) {

		return aR.findByNamePersona(namePersona);
	}

}
