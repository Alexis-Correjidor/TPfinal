package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.service.ITestimonioService;

@Controller
@RequestMapping("/testimonio")
public class TestimonioController {
	
	@Autowired
	private ITestimonioService testimonioService;
	
	@GetMapping("/listado")
	public String GetTestimonioPage(Model model) {
		model.addAttribute("testimonio", testimonioService.getLista());
		return "testimonio";
	}
	
	@GetMapping("/nuevo")
	public String GetNuevoTestimonioPage(Model model, @PathVariable(value="identificador") Long id) {
		model.addAttribute("usu", testimonioService.GetByusuario(id));
		model.addAttribute("testimoni", testimonioService.GetTestimonio());
		boolean edicion = false;
		model.addAttribute("edicion", edicion);
		return "nuevoTestimonio";
	}
	
	@PostMapping("/guardar")
	public ModelAndView getGuardarTestimonioPage(@ModelAttribute("testimoni")Testimonio testimonio) {
		ModelAndView modelView = new ModelAndView("testimonio");
		testimonioService.guardar(testimonio);
		modelView.addObject("testimonio", testimonioService.getLista());
		return modelView;
	}
	
	@GetMapping("/modificar/{id}")
	public String GetModificarTestomonioPage(Model model, @PathVariable(value="id") Long id){
		boolean edicion = true;
		model.addAttribute("testimoni", testimonioService.GetBy(id));
		model.addAttribute("edicion", edicion);
		return "nuevoTestimonio";
	}
	
	@PostMapping("/modificar")
	public String ModificarTestimonio(@ModelAttribute("testimoni")Testimonio testimonio) {
		testimonioService.modificar(testimonio);
		return "redirect:/testimonio/listado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String EliminarTestimonio(@PathVariable(value="id") Long id) {
		Testimonio testimonioEncontrado = testimonioService.GetBy(id);
		testimonioService.eliminar(testimonioEncontrado);
		return "redirect:/sucursal/listado";
	}
}
