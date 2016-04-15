/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.FactorySingleton;
import model.Filial;

/**
 *
 * @author Maurílio
 */
public class JpaFilialDAO implements DAO<Filial> {

    @Override
    public int gravar(Filial entidade) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        if (entidade.getCodFilial() == 0) {
            em.persist(entidade);
        } else {
            em.merge(entidade);
        }
        em.getTransaction().commit();
        em.close();
        return entidade.getCodFilial();
    }

    @Override
    public List<Filial> listarTodos() {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        Query query = em.createNamedQuery("Filial.findAll");
        return query.getResultList();
    }

    @Override
    public Filial busca(int id) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        return em.find(Filial.class, id);
    }

    @Override
    public void excluir(Filial entidade) {
        entidade = busca(entidade.getCodFilial());
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        entidade = em.merge(entidade);
        em.remove(entidade);
        em.getTransaction().commit();
        em.close();
    }

}
