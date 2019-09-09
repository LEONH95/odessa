/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.odessa.Usuario;
import com.odessa.UsuarioArea;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CDIS 2019
 */
@Stateless
public class UsuarioAreaFacade extends AbstractFacade<UsuarioArea> {

    @PersistenceContext(unitName = "com.odessa_ODESSA-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioAreaFacade() {
        super(UsuarioArea.class);
    }
    
    public UsuarioArea findByUsuarioId(String usuario){
        
        TypedQuery <UsuarioArea> query;
        query = em.createQuery("SELECT ua From UsuarioArea ua Where ua.idUsuario.usuario=:usuario", UsuarioArea.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
        
    }
    
}
