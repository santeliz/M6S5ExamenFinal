/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.rentaautos.modelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.rentaautos.entidades.DetAccesorios;

/**
 *
 * @author JAVA
 */
@Stateless
public class DetAccesoriosFacade extends AbstractFacade<DetAccesorios> {

    @PersistenceContext(unitName = "M6S5ExamenFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetAccesoriosFacade() {
        super(DetAccesorios.class);
    }
    
}
