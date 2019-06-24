package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Auditor;

public interface AuditorRepository extends JpaRepository<Auditor, Integer>{

	@Query("from Auditor a where a.dniPersona like %:dniPersona%")
	List<Auditor> findByDniPersona(String dniPersona);

	@Query("from Auditor a where a.namePersona like %:namePersona%")
	List<Auditor> findByNamePersona(String namePersona);
	
	public List<Auditor> findByNamePersonaLikeIgnoreCase(String term);
	
}
