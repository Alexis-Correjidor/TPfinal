package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
@Service
public interface IIngredienteService {
	public List<Ingrediente> getAllIngredientes();
	public Ingrediente findIngredienteById(Long id);
	public Ingrediente findIngrediente();
	public void addIngrediente(Ingrediente ingrediente);
	public void modifyIngrediente(Ingrediente ingrediente);
	public void deleteIngrediente(Ingrediente ingrediente);
}
