package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Gerente;

@Repository
public interface GerenteAreaRepository extends JpaRepository<Gerente, Integer> {

	@Query("from Gerente g where g.dniPersona like %:dniPersona%")
	List<Gerente> findByDniPersona(String dniPersona);

	@Query("from Gerente g where g.namePersona like %:namePersona%")
	List<Gerente> findByNamePersona(String namePersona);

}
