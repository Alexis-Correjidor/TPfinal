package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;

@Service
public class ITestimonioServiceImp implements ITestimonioService {

	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	@Override
	public List<Testimonio> getLista() {
		return testimonioRepository.findByEstado(true);
	}

	@Override
	public void guardar(Testimonio testimonio) {
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
	}

	@Override
	public Testimonio GetBy(Long id) {
		return testimonioRepository.findById(id).get();
	} 

	@Override
	public void modificar(Testimonio testimonio) {
		testimonio.setEstado(true);
		testimonioRepository.save(testimonio);
	}

	@Override
	public void eliminar(Testimonio testimonio) {
		testimonio.setEstado(false);
		testimonioRepository.save(testimonio);
	}

	@Override
	public Testimonio GetTestimonio() {
		return testimonio;
	}


}
