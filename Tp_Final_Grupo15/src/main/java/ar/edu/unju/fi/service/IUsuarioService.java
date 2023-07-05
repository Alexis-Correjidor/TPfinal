package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Usuario;

public interface IUsuarioService {
	
	//#region Methods
    void guardar(Usuario usuario);
    
    List<Usuario> getlista();
    
    Usuario getById(long identificador);

    void eliminar(Usuario usuario);
    
    Usuario getUsuario();

    //#endregion
	
	
}
