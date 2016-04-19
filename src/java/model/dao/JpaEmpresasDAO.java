/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Empresa;
import model.FactorySingleton;

/**
 *
 * @author Maurílio
 */
public class JpaEmpresasDAO implements DAO<Empresa>{


    @Override
    public int gravar(Empresa entidade) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        if (entidade.getCodEmpresa()!=null) {
            em.merge(entidade);
        }else{
            em.persist(entidade);
        }
        em.getTransaction().commit();
        em.close();
        return entidade.getCodEmpresa();
    }

    @Override
    public List<Empresa> listarTodos() {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        Query query = em.createNamedQuery("Empresa.findAll");
        return query.getResultList();
    }

    @Override
    public Empresa busca(int id) {
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        return em.find(Empresa.class, id);
    }

    @Override
    public void excluir(Empresa entidade) {
        entidade = busca(entidade.getCodEmpresa());
        EntityManager em = FactorySingleton.getInstanceFactory().getEntityManager();
        em.getTransaction().begin();
        entidade=em.merge(entidade);
        em.remove(entidade);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Empresa> findByNamedQuery(String s, Map<String, Object> map, int maxResults) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
