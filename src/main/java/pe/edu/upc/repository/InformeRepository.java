package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Informe;


@Repository
public interface InformeRepository  extends JpaRepository<Informe, Integer>{

	@Query("from Informe i where i.auditor.namePersona like %:namePersona%")
	List<Informe> buscarAuditor(@Param("namePersona") String namePersona);
	
}
