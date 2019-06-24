package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

	@Query("select d from Detalle d where d.resultadoAuditoria like %?1%")
	public List<Detalle> findByResultadoAuditoria(String resultadoAuditoria);

}
