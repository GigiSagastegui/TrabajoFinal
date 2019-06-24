package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Auditoria;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>{

	@Query("from Auditoria a where a.estado like %:estado%")
	List<Auditoria> findByEstado(String estado);
	
	
	@Query("from Auditoria a where a.descripcionAuditoria like %:descripcionAuditoria%")
	List<Auditoria> findByDescripcionAuditoria(String descripcionAuditoria);
	
	
	@Query("from Auditoria a where a.prioridad like %:prioridad%")
	List<Auditoria> findByPrioridad(String prioridad);
	
	@Query("from Auditoria a where a.resultado like %:resultado%")
	List<Auditoria> findByResultado(String resultado);
	
	public List<Auditoria> findByDescripcionAuditoriaLikeIgnoreCase(String term);
}
