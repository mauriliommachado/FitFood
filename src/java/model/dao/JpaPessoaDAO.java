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
import model.FactorySingleton;
import model.Pessoa;

/**
 *
 * @author Maurílio
 */
public class JpaPessoaDAO implements DAO<Pessoa> {

    @Override
    public int gravar(Pessoa entidade) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        if (entidade.getCodPessoa() != 0) {
            em.merge(entidade);
        } else {
            em.persist(entidade);
        }
        em.getTransaction().commit();
        em.close();
        return entidade.getCodPessoa();
    }

    @Override
    public List<Pessoa> listarTodos() {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        Query query = em.createNamedQuery("Pessoa.findAll");
        return query.getResultList();
    }

    @Override
    public Pessoa busca(int id) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        return em.find(Pessoa.class, id);
    }

    @Override
    public void excluir(Pessoa entidade) {
        entidade = busca(entidade.getCodPessoa());
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        entidade = em.merge(entidade);
        em.remove(entidade);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Pessoa> findByNamedQuery(String namedQuery, Map<String, Object> namedParams, int maxResults) {
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
            List<Pessoa> returnList = (List<Pessoa>) query.getResultList();
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
