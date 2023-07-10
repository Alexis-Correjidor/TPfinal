package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.service.IImcService;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/gestion")
public class GestionController {

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IImcService imcService;
	
	@Autowired
	IRecetaService recetaService;
	
	@Autowired
	IIngredienteService ingredienteService;
	
	@Autowired
	ITestimonioService testimonioService;
	
	@GetMapping("/usuarios")
	public String getUsuariosPage(Model model) {
		model.addAttribute("usuarios",usuarioService.getlista());
		return "usuarios";
	}
	
	@GetMapping("/indices")
	public String getIndicesPage(Model model) {
		model.addAttribute("indices",imcService.getlista());
		return "indices_mc";
	}
	
	@GetMapping("/recetas")
	public String getRecetasPage(Model model) {
		model.addAttribute("recetas",recetaService.getAllRecetas());
		return "recetas";
	}	
	
	@GetMapping("/ingredientes")
	public String getIngredientesPage(Model model) {
		model.addAttribute("ingredientes",ingredienteService.getAllIngredientes());
		return "ingredientes";
	}
	
	@GetMapping("/testimonios")
	public String getTestimoniosPage(Model model) {
		model.addAttribute("testimonios",testimonioService.getLista());
		return "testimonios";
	}	
}
