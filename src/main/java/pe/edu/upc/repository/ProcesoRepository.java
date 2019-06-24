package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Proceso;

@Repository
public interface ProcesoRepository  extends JpaRepository<Proceso, Integer>{
	@Query("select p from Proceso p where p.area.nombreArea like %?1%")
	public List<Proceso> buscarArea(String nombreArea);
}
