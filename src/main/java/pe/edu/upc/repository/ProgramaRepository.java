package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Programa;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer>{

}
