package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{

	@Query("from Administrador a where a.dniPersona like %:dniPersona%")
	List<Administrador> findByDniPersona(String dniPersona);

	@Query("from Administrador a where a.namePersona like %:namePersona%")
	List<Administrador> findByNamePersona(String namePersona);
	
	public List<Administrador> findByNamePersonaLikeIgnoreCase(String term);

	
}
