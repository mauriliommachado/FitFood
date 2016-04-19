/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author Maurílio
 * @param <E>
 */
public interface DAO<E> {
    
    public int gravar(E entidade);
    public List<E> listarTodos();
    public E busca(int id);
    public void excluir(E entidade);
    public abstract List<E> findByNamedQuery(String s, Map<String, Object> map, int maxResults);


    
}
