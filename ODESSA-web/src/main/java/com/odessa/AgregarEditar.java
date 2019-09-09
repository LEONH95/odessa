/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odessa;

import com.odessa.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author CDIS 2019
 */
@Named(value = "agregarEditar")
@SessionScoped
public class AgregarEditar implements Serializable {

    /**
     * Creates a new instance of AgregarEditar
     */
    @EJB
    private UsuarioController usuarioController = new UsuarioController();
    private Usuario usuarioEntidad = new Usuario();
   
    
    public void insertar(){
        
//        getUsuarioController().insertar(usuarioEntidad);
    }

    /**
     * @return the usuarioController
     */
    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    /**
     * @param usuarioController the usuarioController to set
     */
    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    /**
     * @return the usuarioEntidad
     */
    public Usuario getUsuarioEntidad() {
        return usuarioEntidad;
    }

    /**
     * @param usuarioEntidad the usuarioEntidad to set
     */
    public void setUsuarioEntidad(Usuario usuarioEntidad) {
        this.usuarioEntidad = usuarioEntidad;
    }

  
    
    
    
}
