package com.odessa;

import com.odessa.Usuario;
import com.odessa.util.JsfUtil;
import com.odessa.util.JsfUtil.PersistAction;
import com.facade.UsuarioAreaFacade;
import com.facade.UsuarioFacade;
import com.odessa.UsuarioArea;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private com.facade.UsuarioFacade ejbFacade;
    @EJB
    private com.facade.UsuarioAreaFacade ejbFacade2;
    private List<Usuario> items = null;
    private Usuario selected;
    private Usuario usuario = new Usuario();
    private UsuarioArea usuarioArea = new UsuarioArea();
    private boolean confirm = false;
    
    private String username;
    private String contrasena;
    
    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
    
 
    
    public UsuarioController() {
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }
     
     private UsuarioAreaFacade getFacade2() {
        return ejbFacade2;
    }
     
    public String insertar(){
        
        getUsuario().setNumeroUnico("3");
        
        getUsuarioArea().setIdUsuario(usuario);
        
        
        getFacade().create(usuario);
        getFacade2().create(usuarioArea);
        
        setConfirm(false);
        return "resumenAgregarEditarAdminCliente";
    }
    
    public String prepareEdit(Usuario u){
        setConfirm(true);
        usuario = u;
        String nombreUsuario = usuario.getUsuario();
        usuarioArea = getFacade2().findByUsuarioId(nombreUsuario);
        return "agregarEditarAdminCliente1";
        
    }
    
    public void edit(){
        getFacade().edit(usuario);
        getFacade2().edit(usuarioArea);
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    /*
    * Funcion de login: inicia sesion con un usuario siempre y cuando sea administrador
    */
    public String login() {
        FacesMessage msg;
        usuario = getFacade().findByUserAndPass(getUsername(), getContrasena());
        if(usuario == null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario o contraseña son incorrectos", "");
            FacesContext.getCurrentInstance().addMessage("login", msg);
            return null;
        } else {
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionUsuario", usuario);
             return "agregarEditarAdminCliente/mostrarAgregarEditarAdminCliente";
        }
    }
    
    /*
    * Funcion de eliminar: elimina usuarios por medio del id proporcionado
    */
    public void eliminar(int id) {
        FacesMessage msg;
        try {
            getFacade().remove(getUsuario(id));
            msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "El registro de " + getUsuario().getNombre() 
                                        + " fue eliminado exitosamente. ", "");
            FacesContext.getCurrentInstance().addMessage("List", msg);
        } catch (Exception e) {
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "El registro de " + getUsuario().getNombre() 
                                        + " no pudo ser eliminado, contacte con soporte. ","");
            FacesContext.getCurrentInstance().addMessage("List", msg);
        }
      
    }
    public String prepareDelete(Usuario u) {
        usuario = u;
        setConfirm(true);
        return "List";
    }    
    
        public void eliminar() {    
        FacesMessage msg;
        try {
            getFacade().remove(usuario);
            setConfirm(false);
            msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "El registro de " + getUsuario().getNombre() 
                                        + " fue eliminado exitosamente. ", "");
            FacesContext.getCurrentInstance().addMessage("List", msg);
        } catch (Exception e) {
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "El registro de " + getUsuario().getNombre() 
                                        + " no pudo ser eliminado, contacte con soporte. ","");
            FacesContext.getCurrentInstance().addMessage("List", msg);
        }
      
    }

   

    /*
    * funcion de mainClean: Limpia la pantalla
    */        
    public String mainClean(String url) {
        setUsuario(new Usuario());
        setConfirm(false);
        return url;
    }
    
    /*
    * Funcion de consultarGeneral: trae una lista de la bd con todos los valores
    */    
    public List<Usuario> consultarGeneral() {
        return ejbFacade.consultarGeneral();
    }
            
    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioArea
     */
    public UsuarioArea getUsuarioArea() {
        return usuarioArea;
    }

    /**
     * @param usuarioArea the usuarioArea to set
     */
    public void setUsuarioArea(UsuarioArea usuarioArea) {
        this.usuarioArea = usuarioArea;
    }

  

}
