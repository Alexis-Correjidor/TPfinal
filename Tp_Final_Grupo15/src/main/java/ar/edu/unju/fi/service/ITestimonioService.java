package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Testimonio;

public interface ITestimonioService {

	List<Testimonio> getLista();
	
	void guardar (Testimonio testimonio);
	
	Testimonio GetBy(Long id);
	
	void modificar (Testimonio testimonio);
	
	void eliminar (Testimonio testimonio);
	
	Testimonio GetTestimonio();
	
	Testimonio GetByusuario(Long id);
}
