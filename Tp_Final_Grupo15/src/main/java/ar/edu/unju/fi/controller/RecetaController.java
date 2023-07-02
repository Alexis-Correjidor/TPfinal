package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RecetaController {
	
	@GetMapping("/Receta")
	public String getRecetaPage() {
		return "receta";
	}
}
