package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
