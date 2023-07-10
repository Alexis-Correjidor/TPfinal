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
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {
	
	@Autowired
	private IIngredienteService ingredienteService;

	@Autowired
	private Ingrediente unIngrediente;
	
	@GetMapping("/lista")
	public ModelAndView getPageIngrediente() {
		ModelAndView mav = new ModelAndView("ingredientes");
		mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		return mav;
	}
	
	@GetMapping("/formulario")
	public ModelAndView getPageFormIngrediente() {
		unIngrediente = new Ingrediente();
		ModelAndView mav = new ModelAndView("nuevoIngrediente");
		mav.addObject("unIngrediente", unIngrediente);
		return mav;
	}
	
	@PostMapping("/guardar")
	public ModelAndView getSaveIngredientePage(@Valid @ModelAttribute("unIngrediente") Ingrediente ingrediente, BindingResult result) {
		ModelAndView mav;
		if(result.hasErrors()) {
			mav = new ModelAndView("nuevoIngrediente");
		}else {
		ingredienteService.addIngrediente(ingrediente);
		mav = new ModelAndView("ingredientes");
		mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		}
		return mav;
	}
	
	@GetMapping("/modificar/{id}")
	public ModelAndView getPageEditIngrediente(@PathVariable("id") Long id) {
		unIngrediente = ingredienteService.findIngredienteById(id);
		ModelAndView mav = new ModelAndView("nuevoIngrediente");
		mav.addObject("unIngrediente", unIngrediente);
		return mav;
	}
	
	@GetMapping("/eliminar/{id}")
	public String getPageDeleteIngrediente(@PathVariable("id") Long id) {
		unIngrediente = ingredienteService.findIngredienteById(id);
		ingredienteService.deleteIngrediente(unIngrediente);
		return "redirect:/ingrediente/lista";
	}
}
