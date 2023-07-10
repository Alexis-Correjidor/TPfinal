package ar.edu.unju.fi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IImcService;
import ar.edu.unju.fi.service.IUsuarioService;


@Controller
@RequestMapping("/servicio")
public class ServicioController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	IImcService imcService;
	
	@GetMapping("/peso_ideal")
	private String getPesoIdealPage() {
		return "peso_ideal";
	}
	
	@PostMapping("/peso_ideal")
	public ModelAndView getPesoIdealUsuarioPage(@RequestParam("identificador") int identificador) {
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
	
	@GetMapping("/calcular_imc")
	private String getCalcularIMCPage() {
		return "calcular_imc";
	}
	
	@PostMapping("/calcular_imc")
	public ModelAndView getIMCUsuarioPage(@RequestParam("identificador") int identificador) {
	  ModelAndView modelAndView=new ModelAndView("calcular_imc");
	  if(usuarioService.getById(identificador)==null)
	  {
		  modelAndView.addObject("encontrado", false);
	  }
	  else
	  {
		  modelAndView.addObject("listaImc",imcService.getbyUsuario(usuarioService.getById(identificador)));
		  modelAndView.addObject("usuario", usuarioService.getById(identificador));
	  }
	  return modelAndView;
	}
	
	@GetMapping("/calcular_imc/nuevo")
	public ModelAndView getRegistradoPage(@RequestParam("id") long id) {
	    Usuario usuario = usuarioService.getById(id);
	    IndiceMasaCorporal imc=new IndiceMasaCorporal();
	    ModelAndView modelAndView = new ModelAndView("nuevo_imc");
	    imc.setUsuario(usuario);  
	    modelAndView.addObject("imc", imc);
	    modelAndView.addObject("editar", false);
	    return modelAndView;
	}
	
	@PostMapping("/calcular_imc/guardar")
	public ModelAndView getResultadoImcPage(@ModelAttribute(value = "imc") IndiceMasaCorporal imc,
			@RequestParam("peso") double peso){
	  ModelAndView modelAndView=new ModelAndView();
	  imc.setUsuario(usuarioService.getById(imc.getUsuario().getIdentificador()));
	  imc.setFechaIMC(LocalDate.now());
	  imc.setPeso(peso);
	  if(imc.getIdentificador()!=0)
	  {
		  imcService.guardar(imc);
		  modelAndView.setViewName("redirect:/gestion/indices");
		  return modelAndView;
	  }
	  else {
	  imc.setResultado(imc.calcularImc(imc.getUsuario().getEstatura(), peso));
	  imcService.guardar(imc);
	  modelAndView.setViewName("redirect:/servicio/imc_resultado?id="+imc.getIdentificador());
	  return modelAndView;}
	}
	
	@GetMapping("/imc_resultado")
	public ModelAndView getResultadoPage(@RequestParam("id") long id) {
		IndiceMasaCorporal imc=imcService.getById(id);
	    ModelAndView modelAndView = new ModelAndView("ver_imc");
	    modelAndView.addObject("imc",imc);
	    modelAndView.addObject("usuario",imc.getUsuario());
	    return modelAndView;
	}
	
    @GetMapping("/imc/modificar/{identificador}")
    public ModelAndView getModifyIMCPage(
            @PathVariable(value = "identificador") long identificador) {
    	IndiceMasaCorporal imc;
        ModelAndView modelAndView = new ModelAndView();
        boolean edicion = true;

        imc = imcService.getById(identificador);
        modelAndView.setViewName("nuevo_imc");
        modelAndView.addObject("imc", imc);
        modelAndView.addObject("editar", edicion);

        return modelAndView;
    }
    
    @GetMapping("/imc/eliminar/{identificador}")
    public ModelAndView deleteIMC(
            @PathVariable(value = "identificador") long identificador) {
            
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/gestion/indices");
        imcService.eliminar(imcService.getById(identificador));

        return modelAndView;
    }
}
