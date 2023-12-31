package ar.edu.unju.fi.controller;

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

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/registrarme")
public class RegistrarmeController {

	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/formulario")
	public String getRegistrarmePage(Model model) {
		boolean edicion=false;
		model.addAttribute("usuario",usuarioService.getUsuario());
		model.addAttribute("editar", edicion);
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
	    	if(usuario.getIdentificador()==0)
	    	{
	    		usuarioService.guardar(usuario);
		        modelAndView.setViewName("redirect:/registrarme/usuarioregistrado?id=" + usuario.getIdentificador());
	    	}
	    	else
	    	{
	    		usuarioService.guardar(usuario);
		        modelAndView.setViewName("redirect:/gestion/usuarios");
	    	}
	    }

	    return modelAndView;
	}
	
	@GetMapping("/usuarioregistrado")
	public ModelAndView getRegistradoPage(@RequestParam("id") long id) {
	    Usuario usuario = usuarioService.getById(id);

	    ModelAndView modelAndView = new ModelAndView("registrado");
	    modelAndView.addObject("usuario", usuario);

	    return modelAndView;
	}
	
    @GetMapping("/modificar/{identificador}")
    public ModelAndView getModifyUsuarioPage(
            @PathVariable(value = "identificador") long identificador) {
    	Usuario usuario;
        ModelAndView modelAndView = new ModelAndView();
        boolean edicion = true;

        usuario = usuarioService.getById(identificador);
        modelAndView.setViewName("registrarme");
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("editar", edicion);

        return modelAndView;
    }
    
    @GetMapping("/eliminar/{identificador}")
    public ModelAndView deleteUsuario(
            @PathVariable(value = "identificador") long identificador) {
            
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/gestion/usuarios");
        usuarioService.eliminar(usuarioService.getById(identificador));

        return modelAndView;
    }
}
