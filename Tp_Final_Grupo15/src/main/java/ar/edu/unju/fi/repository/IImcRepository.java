package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

@Repository
public interface IImcRepository extends CrudRepository<IndiceMasaCorporal, Long>{
	public List<IndiceMasaCorporal> findByEstado(boolean estado);
	public List<IndiceMasaCorporal> findByUsuario(Usuario usuario);
}
