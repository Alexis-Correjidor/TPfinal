package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;
@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private Usuario usuario;
	
	@Override
	public void guardar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> getlista() {
		return usuarioRepository.findByEstado(true);
	}

	@Override
	public Usuario getById(long identificador) {
		return usuarioRepository.findById(identificador).get();
	}

	@Override
	public void eliminar(Usuario usuario) {
		usuario.setEstado(false);
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

}
