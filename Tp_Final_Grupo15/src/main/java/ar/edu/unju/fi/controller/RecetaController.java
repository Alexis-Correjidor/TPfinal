package ar.edu.unju.fi.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import ar.edu.unju.fi.util.UploadFile;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class RecetaController {
	
	@Autowired
	UploadFile uploadFile;
	
	@Autowired
	IIngredienteService ingredienteService;
	
	@Autowired
	IRecetaService recetaService;
	
	@Autowired
	Receta unaReceta;
	
	@Autowired 
	Ingrediente unIngrediente;
	
	@GetMapping("/Receta")
	public ModelAndView getRecetaPage() {
		ModelAndView mav = new ModelAndView("receta");
		mav.addObject("recetas",recetaService.getAllRecetas());
		return mav;
	}
	
	@GetMapping("/receta/lista")
	public ModelAndView getPageRecetas() {
		ModelAndView mav = new ModelAndView("listaRecetas");
		mav.addObject("recetas", recetaService.getAllRecetas());
		mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		return mav;
	}
	
	@GetMapping("/receta/{categoria}")
	public ModelAndView getPageRecetasByCategoria(@PathVariable("categoria")String categoria) {
		boolean listar=true;
		List<Receta> recetas = recetaService.getList(categoria);
		ModelAndView mav = new ModelAndView("listaRecetas");
		mav.addObject("recetas", recetas);
		mav.addObject("listar", listar);
		return mav;
	}
	
	
	@GetMapping("/receta/formulario")
	public ModelAndView getPageFormReceta() {
		unaReceta = new Receta();
		ModelAndView mav = new ModelAndView("nuevaReceta");
		mav.addObject("unaReceta", unaReceta);
		mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		return mav;
	}
	
	@PostMapping("/receta/guardar") 
	public ModelAndView postSaveRecetaPage(@Valid @ModelAttribute("unaReceta")Receta receta, 
BindingResult result,
					@RequestParam("file")MultipartFile image) throws Exception {
		ModelAndView mav;
		if(result.hasErrors()) {
			mav = new ModelAndView("nuevaReceta");
			mav.addObject("receta", receta);
			mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		}else {
		
			if(receta.getId() != null) {
				unaReceta = recetaService.getRecetaById(receta.getId());
				if(!image.isEmpty()) {
					if(unaReceta.getImagen() != null && unaReceta.getImagen().length() > 0){
						uploadFile.delete(unaReceta.getImagen());
						String uniqueFile = uploadFile.copy(image);
						receta.setImagen(uniqueFile);
					} else {
						if(unaReceta.getImagen() != null)
							receta.setImagen(unaReceta.getImagen());
					}
				}	
				} else {
					if(!image.isEmpty()) {
						String uniqueFileName = uploadFile.copy(image);
						receta.setImagen(uniqueFileName);
					}
				}
				recetaService.addReceta(receta);
				
				mav = new ModelAndView("listaRecetas");
				mav.addObject("recetas", recetaService.getAllRecetas());
		}
		return mav;
		
	}
	
	@GetMapping("/receta/modificar/{id}")
	public ModelAndView getPageEditReceta(@PathVariable("id") Long id) {
		unaReceta = recetaService.getRecetaById(id);
		ModelAndView mav = new ModelAndView("nuevaReceta");
		mav.addObject("unaReceta", unaReceta);
		mav.addObject("ingredientes", ingredienteService.getAllIngredientes());
		return mav;
	}
	
	@GetMapping("/receta/eliminar/{id}")
	public String getPageDeleteReceta(@PathVariable("id") Long id) {
		unaReceta = recetaService.getRecetaById(id);
		recetaService.deleteReceta(unaReceta);
		return "redirect:/receta/lista";
	}
	
	@GetMapping("/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename){
		Resource resource = null;
		try {
			resource = uploadFile.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		 resource.getFilename() + "\"")
		.body(resource);
	}
}
