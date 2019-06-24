package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>  {

	@Query("select e from Equipo e where e.cargo like %?1%")
	public List<Equipo> findByCargo(String cargo) ;

	public List<Equipo> findByCargoLikeIgnoreCase(String cargo);
	
	@Query("from Equipo e where e.auditor.namePersona like %:namePersona%")
	List<Equipo> buscarAuditoria(@Param("namePersona") String namePersona);
	
	
	
}