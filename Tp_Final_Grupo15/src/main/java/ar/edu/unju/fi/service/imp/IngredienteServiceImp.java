package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;

@Service
public class IngredienteServiceImp implements IIngredienteService{

	@Autowired
	private IIngredienteRepository ingredienteRepository;
	
	@Autowired
	private Ingrediente ingrediente;
	
	@Override
	public List<Ingrediente> getAllIngredientes() {
		List<Ingrediente> ingredientes = (List<Ingrediente>)ingredienteRepository.findAll();
		return ingredientes;
	}

	@Override
	public Ingrediente findIngredienteById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	@Override
	public Ingrediente findIngrediente() {
		return ingrediente;
	}

}
