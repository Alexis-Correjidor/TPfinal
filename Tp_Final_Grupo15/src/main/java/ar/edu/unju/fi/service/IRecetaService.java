package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
@Service
public interface IRecetaService {
	public List<Receta> getList(String categoria);
	public Receta getRecetaById(Long id);
	public Receta getReceta();
	public void addReceta(Receta receta);
	public void modifyReceta(Receta receta);
	public void deleteReceta(Receta receta);
}
