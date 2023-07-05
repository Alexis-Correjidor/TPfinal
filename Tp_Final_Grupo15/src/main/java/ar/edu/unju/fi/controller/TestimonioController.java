package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testimonio")
public class TestimonioController {
	
	@GetMapping("/listado")
	private String GetTestimonioPage() {
		return "testimonio";
	}
}
