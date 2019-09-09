/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.odessa.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CDIS 2019
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.odessa_ODESSA-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario> consultarGeneral() {
        TypedQuery<Usuario> query;
        query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
    
    public Usuario findByUserAndPass(String usuario, String contrasena) {
        TypedQuery<Usuario> query;
        query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario=:usuario AND u.contrasena=:contrasena",Usuario.class);
        query.setParameter("usuario",usuario);
        query.setParameter("contrasena",contrasena);
        
        try {
            return query.getSingleResult();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    
}
