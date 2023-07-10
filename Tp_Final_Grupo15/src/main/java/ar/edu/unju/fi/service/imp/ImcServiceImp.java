package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IImcRepository;
import ar.edu.unju.fi.service.IImcService;

@Service
public class ImcServiceImp implements IImcService {

	@Autowired
	IImcRepository imcRepository;
	
	@Autowired
	IndiceMasaCorporal indicemc;
	
	@Override
	public void guardar(IndiceMasaCorporal imc) {
		// TODO Auto-generated method stub
		imcRepository.save(imc);
	}

	@Override
	public List<IndiceMasaCorporal> getlista() {
		// TODO Auto-generated method stub
		return imcRepository.findByEstado(true);
	}

	@Override
	public IndiceMasaCorporal getById(long identificador) {
		// TODO Auto-generated method stub
		return imcRepository.findById(identificador).get();
	}

	@Override
	public void eliminar(IndiceMasaCorporal imc) {
		// TODO Auto-generated method stub
		imc.setEstado(false);
		imcRepository.save(imc);
	}

	@Override
	public IndiceMasaCorporal getImc() {
		// TODO Auto-generated method stub
		return indicemc;
	}

	@Override
	public List<IndiceMasaCorporal> getbyUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return imcRepository.findByUsuario(usuario);
	}

}
