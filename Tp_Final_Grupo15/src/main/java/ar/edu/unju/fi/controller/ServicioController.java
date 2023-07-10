package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.service.IUsuarioService;


@Controller
@RequestMapping("/servicio")
public class ServicioController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/peso_ideal")
	private String getPesoIdealPage() {
		return "peso_ideal";
	}
	
	@PostMapping("/peso_ideal")
	public ModelAndView manejarFormulario(@RequestParam("identificador") int identificador) {
	  ModelAndView modelAndView=new ModelAndView("peso_ideal");
	  if(usuarioService.getById(identificador)==null)
	  {
		  modelAndView.addObject("encontrado", false);
	  }
	  else
	  {
		  
		  modelAndView.addObject("usuario", usuarioService.getById(identificador));
		  modelAndView.addObject("edad", usuarioService.getById(identificador).getEdad());
		  modelAndView.addObject("peso_ideal", usuarioService.getById(identificador).getPesoIdeal());
	  }
	  return modelAndView;
	}
}
