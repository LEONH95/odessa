/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.odessa.Estatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CDIS 2019
 */
@Stateless
public class EstatusFacade extends AbstractFacade<Estatus> {

    @PersistenceContext(unitName = "com.odessa_ODESSA-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstatusFacade() {
        super(Estatus.class);
    }
    
    @Override
    public List<Estatus> findAll(){
            Query query;
            query = em.createNamedQuery("Estatus.findAll");
            return query.getResultList();
    }
    
    public void Insert(Estatus es){
        em.persist(es);
    }
    
    public void Update(Estatus es){
        em.merge(es);
    }
    
    public void Delete(Estatus es) {
        em.remove(em.merge(es));
    }

    public Estatus find(Long id) {
        return em.find(Estatus.class, id);
    }
    
}
