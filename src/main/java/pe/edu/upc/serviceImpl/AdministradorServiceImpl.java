package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Administrador;
import pe.edu.upc.repository.AdministradorRepository;
import pe.edu.upc.service.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

	@Autowired
	private AdministradorRepository aR;

	@Override
	@Transactional
	public void insertar(Administrador administrador) {
		
		aR.save(administrador);
	}

	@Override
	@Transactional
	public void modificar(Administrador administrador) {		

		aR.save(administrador);
		
	}

	

	@Override
	public Optional<Administrador> listarId(int idPersona) {
		
		return aR.findById(idPersona);
	}

	@Override
	public List<Administrador> listar() {
		
		return aR.findAll();
	}

	@Override
	public List<Administrador> buscarDni(String dniPersona) {
		
		return aR.findByDniPersona(dniPersona);
	}

	@Override
	public List<Administrador> buscarName(String namePersona) {
		
		return aR.findByNamePersona(namePersona);
	}

	@Override
	public List<Administrador> buscarNombreCaso(String namePersona) {
		
		return aR.findByNamePersonaLikeIgnoreCase(namePersona);
	}
	
	

}
