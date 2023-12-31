package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

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
	    Optional<Usuario> optionalUsuario = usuarioRepository.findById(identificador);
	    
	    if (optionalUsuario.isPresent()) { 
	        Usuario usuario = optionalUsuario.get();
	        
	        if (usuario.isEstado()) { 
	            return usuario;
	        }
	    }
	    
	    return null; // se devuelve null si el usuario no existe o su estado es false
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
