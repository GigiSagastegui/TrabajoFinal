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
	public void insertar(Gerente gerente) {

		gR.save(gerente);

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

}
