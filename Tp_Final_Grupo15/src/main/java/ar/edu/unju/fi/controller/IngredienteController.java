package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class IngredienteController {
	
	@Autowired
	private IIngredienteService ingredienteService;
	
	@Autowired
	private IRecetaService recetaService;
	
	
	@Autowired
	private Ingrediente unIngrediente;
	
	@GetMapping("/ingrediente/formulario")
	public ModelAndView getPageFormIngrediente() {
		unIngrediente = new Ingrediente();
		ModelAndView mav = new ModelAndView("nuevoIngrediente");
		mav.addObject("unIngrediente", unIngrediente);
		mav.addObject("recetas", recetaService.getAllRecetas());
		return mav;
	}
	
	@PostMapping("/ingrediente/guardar")
	public ModelAndView getSaveIngredientePage(@Valid @ModelAttribute("unIngrediente") Ingrediente ingrediente, BindingResult result) {
		ModelAndView mav;
		if(result.hasErrors()) {
			mav = new ModelAndView("unIngrediente");
			mav.addObject("ingrediente", ingrediente);
		}
		ingredienteService.addIngrediente(ingrediente);
		mav = new ModelAndView("receta");
		mav.addObject("receta", ingredienteService.getAllIngredientes());
		return mav;
	}
	
	@GetMapping("/ingrediente/modificar/{id}")
	public ModelAndView getPageEditIngrediente(@PathVariable("id") Long id) {
		unIngrediente = ingredienteService.findIngredienteById(id);
		ModelAndView mav = new ModelAndView("nuevoIngrediente");
		mav.addObject("ingrediente", unIngrediente);
		mav.addObject("recetas", recetaService.getAllRecetas());
		return mav;
	}
}
