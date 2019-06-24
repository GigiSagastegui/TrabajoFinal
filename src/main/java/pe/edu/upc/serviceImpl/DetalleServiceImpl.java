package pe.edu.upc.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Detalle;
import pe.edu.upc.repository.DetalleRepository;
import pe.edu.upc.service.IDetalleService;

@Service
public class DetalleServiceImpl implements IDetalleService{

	@Autowired
	private DetalleRepository dR;
	
	@Override
	@Transactional
	public void insertar(Detalle detalle) {
		dR.save(detalle);
		
	}

	@Override
	@Transactional
	public void modificar(Detalle detalle) {
		dR.save(detalle);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Detalle> listarId(int idDetalle) {
		
		return dR.findById(idDetalle);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Detalle> listar() {
		
		return dR.findAll();
	}

	@Override
	public List<Detalle> buscarResultado(String resultadoAuditoria) {
		
		return dR.findByResultadoAuditoria(resultadoAuditoria);
	}

}
