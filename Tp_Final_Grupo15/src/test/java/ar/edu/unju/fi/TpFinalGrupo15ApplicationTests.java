package ar.edu.unju.fi;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.edu.unju.fi.service.IUsuarioService;
import ar.edu.unju.fi.entity.*;
@SpringBootTest
class TpFinalGrupo15ApplicationTests {

	@Autowired
	IUsuarioService usuarioservice; 
	@Autowired
	Usuario usu;
	@Test
	void guardartest() {
		usu.setNombre("gon");
		usu.setApellido("Velazquez");
		usu.setEmail("glskglsk@gmail.com");
		usu.setTelefono("4918618");
		usu.setEstatura((float)3);
		usu.setSexo("Masculino");
		usu.setFechaNacimiento(LocalDate.of(2004, 1, 1));
		usuarioservice.guardar(usu);
	}

}
