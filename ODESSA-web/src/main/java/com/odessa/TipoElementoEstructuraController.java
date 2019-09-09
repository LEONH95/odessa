package com.odessa;

import com.odessa.TipoElementoEstructura;
import com.odessa.util.JsfUtil;
import com.odessa.util.JsfUtil.PersistAction;
import com.facade.TipoElementoEstructuraFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tipoElementoEstructuraController")
@SessionScoped
public class TipoElementoEstructuraController implements Serializable {

    @EJB
    private com.facade.TipoElementoEstructuraFacade ejbFacade;
    private List<TipoElementoEstructura> items = null;
    private TipoElementoEstructura selected;

    public TipoElementoEstructuraController() {
    }

    public TipoElementoEstructura getSelected() {
        return selected;
    }

    public void setSelected(TipoElementoEstructura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipoElementoEstructuraFacade getFacade() {
        return ejbFacade;
    }

    public TipoElementoEstructura prepareCreate() {
        selected = new TipoElementoEstructura();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TipoElementoEstructuraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TipoElementoEstructuraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TipoElementoEstructuraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoElementoEstructura> getItems() {
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

    public TipoElementoEstructura getTipoElementoEstructura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoElementoEstructura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoElementoEstructura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TipoElementoEstructura.class)
    public static class TipoElementoEstructuraControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoElementoEstructuraController controller = (TipoElementoEstructuraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoElementoEstructuraController");
            return controller.getTipoElementoEstructura(getKey(value));
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
            if (object instanceof TipoElementoEstructura) {
                TipoElementoEstructura o = (TipoElementoEstructura) object;
                return getStringKey(o.getIdTipoElementoEstructura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoElementoEstructura.class.getName()});
                return null;
            }
        }

    }

}
