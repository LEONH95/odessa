/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.odessa.ElementoEstructura;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CDIS 2019
 */
@Stateless
public class ElementoEstructuraFacade extends AbstractFacade<ElementoEstructura> {

    @PersistenceContext(unitName = "com.odessa_ODESSA-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementoEstructuraFacade() {
        super(ElementoEstructura.class);
    }
    
}
