package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.entity.Receta;

public interface IRecetaRepository extends CrudRepository<Receta, Long>  {

	public List<Receta> findByCategoria(String nombre);
	
}
