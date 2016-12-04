/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.rentaautos.modelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.edu.udb.rentaautos.entidades.Marca;

/**
 *
 * @author JAVA
 */
@Stateless
public class MarcaFacade extends AbstractFacade<Marca> {

    @PersistenceContext(unitName = "M6S5ExamenFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaFacade() {
        super(Marca.class);
    }
    
}
