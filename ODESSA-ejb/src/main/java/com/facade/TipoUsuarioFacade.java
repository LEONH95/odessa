/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.odessa.TipoUsuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CDIS 2019
 */
@LocalBean
@Stateless
public class TipoUsuarioFacade extends AbstractFacade<TipoUsuario> {

    @PersistenceContext(unitName = "com.odessa_ODESSA-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoUsuarioFacade() {
        super(TipoUsuario.class);
    }
    
    @Override
    public List<TipoUsuario> findAll(){
            TypedQuery<TipoUsuario> query;
            query = em.createQuery("SELECT tu FROM TipoUsuario tu", TipoUsuario.class);
            return query.getResultList();
    }
    
    public void Insert(TipoUsuario tu){
        em.persist(tu);
    }
    
    public void Update(TipoUsuario tu){
        em.merge(tu);
    }
    
    public void Delete(TipoUsuario tu) {
        em.remove(em.merge(tu));
    }

    public TipoUsuario find(Long id) {
        return em.find(TipoUsuario.class, id);
    }
    
}
