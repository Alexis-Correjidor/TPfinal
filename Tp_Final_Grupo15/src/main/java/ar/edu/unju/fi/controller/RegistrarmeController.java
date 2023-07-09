package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/registrarme")
public class RegistrarmeController {

	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/formulario")
	private String getRegistrarmePage(Model model) {
		model.addAttribute("usuario",usuarioService.getUsuario());
		return "registrarme";
	}
	
	@PostMapping("/guardar")
	public ModelAndView saveNewUsuario(
	        @Valid @ModelAttribute(value = "usuario") Usuario usuario,
	        BindingResult resultadoValidacion) {

	    ModelAndView modelAndView = new ModelAndView();

	    if (resultadoValidacion.hasErrors()) {
	        modelAndView.setViewName("registrarme");
	    } else {
	        usuarioService.guardar(usuario);
	        modelAndView.setViewName("redirect:/registrarme/usuarioregistrado?id=" + usuario.getIdentificador());
	    }

	    return modelAndView;
	}
	
	@GetMapping("/usuarioregistrado")
	private ModelAndView getRegistradoPage(@RequestParam("id") long id) {
	    Usuario usuario = usuarioService.getById(id);

	    ModelAndView modelAndView = new ModelAndView("registrado");
	    modelAndView.addObject("usuario", usuario);

	    return modelAndView;
	}
}
