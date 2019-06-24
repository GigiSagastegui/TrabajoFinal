package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Integer>{
	@Query("select a from Area a where a.nombreArea like %?1%")
	public List<Area> findByNombreArea(String term);
	
	public List<Area> findByNombreAreaLikeIgnoreCase(String term);
	
}
