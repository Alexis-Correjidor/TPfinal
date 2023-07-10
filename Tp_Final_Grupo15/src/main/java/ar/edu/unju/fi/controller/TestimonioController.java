package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/testimonio")
public class TestimonioController {
	
	@Autowired
	private ITestimonioService testimonioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Testimonio testimonio;
	
	@GetMapping("/listado")
	public String GetTestimonioPage(Model model) {
		model.addAttribute("testimonios", testimonioService.getLista());
		return "testimonio";
	}
	
	@GetMapping("/nuevo")
	private String GetNuevoTestimonioPage(Model model) {
		model.addAttribute("testimonio", testimonioService.GetTestimonio());
		return "nuevoTestimonio";
	}
	
	@PostMapping("/nuevo")
	public ModelAndView imonioPage(@RequestParam("identificador") int identificador) {
		ModelAndView modelView = new ModelAndView("nuevoTestimonio");
		if(usuarioService.getById(identificador)==null) {
			modelView.addObject("encontrado", false);
		}
		else {
			modelView.addObject("usuario", usuarioService.getById(identificador));
			modelView.addObject("idusu", usuarioService.getById(identificador));
		}
		boolean edicion = false;
		modelView.addObject("edicion", edicion);
		modelView.addObject("testimonio", testimonioService.GetTestimonio());
		return modelView;
	}
	
	@PostMapping("/guardar")
	public ModelAndView getGuardarTestimonioPage(@ModelAttribute("testimonio")Testimonio testimonio) {
		ModelAndView modelView = new ModelAndView("testimonio");
		testimonioService.guardar(testimonio);
		modelView.addObject("testimonios", testimonioService.getLista());
		return modelView;
	}
	
	@GetMapping("/modificar/{id}")
	public String GetModificarTestomonioPage(Model model, @PathVariable(value="id") Long id){
		boolean edicion = true;
		model.addAttribute("testimonio", testimonioService.GetBy(id));
		model.addAttribute("edicion", edicion);
		return "nuevoTestimonio";
	}
	
	@PostMapping("/modificar")
	public String ModificarTestimonio(@ModelAttribute("testimonio")Testimonio testimonio) {
		testimonioService.modificar(testimonio);
		return "redirect:/testimonio/listado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id") Long id) {
		Testimonio testimonioEncontrado = testimonioService.GetBy(id);
		testimonioService.eliminar(testimonioEncontrado);
		return "redirect:/testimonio/listado";
	}
}
