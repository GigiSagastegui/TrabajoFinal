package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Gerente;
import pe.edu.upc.repository.GerenteAreaRepository;
import pe.edu.upc.service.IGerenteAreaService;

@Service
public class GerenteAreaServiceImpl implements IGerenteAreaService {

	@Autowired
	private GerenteAreaRepository gR;

	@Override
	@Transactional
	public boolean insertar(Gerente gerente) {

		Gerente objGerente = gR.save(gerente);
		if (objGerente == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	@Transactional
	public void modificar(Gerente gerente) {
		gR.save(gerente);

	}

	@Override
	public Optional<Gerente> listarId(int idPersona) {

		return gR.findById(idPersona);
	}

	@Override
	public List<Gerente> listar() {

		return gR.findAll();
	}

	@Override
	public List<Gerente> buscarDni(String dniPersona) {

		return gR.findByDniPersona(dniPersona);
	}

	@Override
	public List<Gerente> buscarName(String namePersona) {

		return gR.findByNamePersona(namePersona);
	}

	@Override
	public List<Gerente> buscarNombreCaso(String namePersona) {
		// TODO Auto-generated method stub

		return gR.findByNamePersonaLikeIgnoreCase(namePersona);
	}

}
