/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Telefone;
import model.FactorySingleton;

/**
 *
 * @author Maurílio
 */
public class JpaTelefoneDAO implements DAO<Telefone> {

    @Override
    public int gravar(Telefone entidade) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        if (entidade.getCodTelefone() != null) {
            em.merge(entidade);
        } else {
            em.persist(entidade);
        }
        em.getTransaction().commit();
        em.close();
        return entidade.getCodTelefone();
    }

    @Override
    public List<Telefone> listarTodos() {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        Query query = em.createNamedQuery("Telefone.findAll");
        return query.getResultList();
    }

    @Override
    public Telefone busca(int id) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        return em.find(Telefone.class, id);
    }

    @Override
    public void excluir(Telefone entidade) {
        entidade = busca(entidade.getCodTelefone());
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        entidade = em.merge(entidade);
        em.remove(entidade);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Telefone> findByNamedQuery(String namedQuery, Map<String, Object> namedParams, int maxResults) {
        try {
            EntityManager entityManager = FactorySingleton.getInstanceFactory().getEntityManager();
            Query query = entityManager.createNamedQuery(namedQuery);
            if (namedParams != null) {
                Map.Entry<String, Object> mapEntry;
                for (Iterator it = namedParams.entrySet().iterator();
                        it.hasNext();
                        query.setParameter((String) mapEntry.getKey(), mapEntry.getValue())) {
                    mapEntry = (Map.Entry<String, Object>) it.next();
                }
            }
            return (List<Telefone>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
