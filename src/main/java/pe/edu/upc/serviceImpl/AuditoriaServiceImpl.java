package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Auditoria;
import pe.edu.upc.repository.AuditoriaRepository;
import pe.edu.upc.service.IAuditoriaService;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService{

	@Autowired
	private AuditoriaRepository aR;
	
	@Override
	@Transactional
	public void insertar(Auditoria auditoria) {
		aR.save(auditoria);
	}

	@Override
	@Transactional
	public void modificar(Auditoria auditoria) {
		aR.save(auditoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Auditoria> listarId(int idAuditoria) {
		return aR.findById(idAuditoria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Auditoria> listar() {
		return aR.findAll();
	}

	@Override
	public List<Auditoria> buscarEstado(String estado) {
		return aR.findByEstado(estado);
	}

	@Override
	public List<Auditoria> buscarResultado(String resultado) {
		// TODO Auto-generated method stub
		return aR.findByResultado(resultado);
	}

	@Override
	public List<Auditoria> buscarPrioridad(String prioridad) {
		// TODO Auto-generated method stub
		return aR.findByPrioridad(prioridad);
	}

	@Override
	public List<Auditoria> buscarDescripcion(String descripcionAuditoria) {
		// TODO Auto-generated method stub
		return aR.findByDescripcionAuditoria(descripcionAuditoria);
	}

	@Override
	public List<Auditoria> buscarNombreCaso(String descripcionAuditoria) {
		// TODO Auto-generated method stub
		return aR.findByDescripcionAuditoriaLikeIgnoreCase(descripcionAuditoria);
	}

}
