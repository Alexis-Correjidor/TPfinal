package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

public interface IImcService {

	//#region Methods
    void guardar(IndiceMasaCorporal imc);
    
    List<IndiceMasaCorporal> getlista();
    
    IndiceMasaCorporal getById(long identificador);

    void eliminar(IndiceMasaCorporal imc);
    
    IndiceMasaCorporal getImc();
    
    List<IndiceMasaCorporal> getbyUsuario(Usuario usuario);

    //#endregion
}
