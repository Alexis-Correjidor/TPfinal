package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;

@Service
public class RecetaServiceImp implements IRecetaService{
	
	@Autowired
	private IRecetaRepository recetaRepository;
	
	@Autowired
	private Receta receta;
	
	@Override
	public Receta getRecetaById(Long id) {
		return recetaRepository.findById(id).get();
	}

	@Override
	public Receta getReceta() {
		return receta;
	}

	@Override
	public void addReceta(Receta receta) {
		recetaRepository.save(receta);
	}

	@Override
	public void modifyReceta(Receta receta) {
		recetaRepository.save(receta);
	}

	@Override
	public void deleteReceta(Receta receta) {
		recetaRepository.delete(receta);
	}

	@Override
	public List<Receta> getList(String categoria) {
		return recetaRepository.findByCategoria(categoria);
	}

}
