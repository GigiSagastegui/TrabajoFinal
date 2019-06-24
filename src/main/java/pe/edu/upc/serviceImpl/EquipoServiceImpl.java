package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Equipo;
import pe.edu.upc.repository.EquipoRepository;
import pe.edu.upc.service.IEquipoService;

@Service
public class EquipoServiceImpl implements IEquipoService {

	@Autowired
	private EquipoRepository eR;

	@Override
	@Transactional
	public void insertar(Equipo equipo) {
		eR.save(equipo);
	}

	@Override
	@Transactional
	public void modificar(Equipo equipo) {
		eR.save(equipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Equipo> listarId(int idEquipo) {
		return eR.findById(idEquipo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> listar() {
		return eR.findAll(Sort.by(Sort.Direction.ASC, "cargo"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> buscarCargo(String cargo) {
		return eR.findByCargo(cargo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> buscarLikeIgnoreCase(String cargo) {
		return eR.findByCargoLikeIgnoreCase(cargo);

	}

	@Override
	public List<Equipo> buscarAuditoria(String namePersona) {
		return eR.buscarAuditoria(namePersona);
	}


}
