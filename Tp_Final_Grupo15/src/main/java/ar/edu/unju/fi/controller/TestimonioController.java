package ar.edu.unju.fi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

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
			modelView.addObject("name", usuarioService.getById(identificador).getNombre());
			testimonio.setNombre(usuarioService.getById(identificador).getNombre());
		}
		Usuario usuario = usuarioService.getById(identificador);
		
		testimonio.setUsuario(usuario);
		boolean edicion = false;
		modelView.addObject("edicion", edicion);
		modelView.addObject("testimonio", testimonioService.GetTestimonio());
		return modelView;
	}
	
	@PostMapping("/guardar")
	public ModelAndView getGuardarTestimonioPage(@Valid @ModelAttribute("testimonio")Testimonio testimonio, BindingResult result) {
		ModelAndView modelView = new ModelAndView("testimonio");
		if(result.hasErrors()) {
			modelView.setViewName("nuevoTestimonio");
			modelView.addObject("testimonio", testimonio);
			return modelView;
		}else {
		testimonio.setFechaComentario(LocalDate.now());
		testimonio.setUsuario(usuarioService.getById(testimonio.getUsuario().getIdentificador()));
		testimonioService.guardar(testimonio);
		modelView.addObject("testimonios", testimonioService.getLista());
		}
		return modelView;
		
	}
	
	@GetMapping("/modificar/{id}")
	public ModelAndView GetModificarTestomonioPage( @PathVariable(value="id") Long id){
		ModelAndView modelView = new ModelAndView("nuevoTestimonio");
		Testimonio testimonio;
		boolean edicion = true;
		testimonio = testimonioService.GetBy(id);
		modelView.addObject("testimonio", testimonio);
		modelView.addObject("edicion", edicion);
		return modelView;
	}
	
	@PostMapping("/modificar")
	public String ModificarTestimonio(@Valid @ModelAttribute("testimonio")Testimonio testimonio, BindingResult result) {
		if(result.hasErrors()) {
			return "nuevoTestimonio";
		}
		testimonioService.guardar(testimonio);
		return "redirect:/testimonio/listado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id") Long id) {
		Testimonio testimonioEncontrado = testimonioService.GetBy(id);
		testimonioService.eliminar(testimonioEncontrado);
		return "redirect:/testimonio/listado";
	}
}
