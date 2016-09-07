/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.dao.DAO;
import model.Categoria;
import model.dao.JpaCategoriaDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleCategoria {

    static DAO dao = new JpaCategoriaDAO();

    public static List<Categoria> limpaCategoria(List<Categoria> list) {
        for (Categoria categoria : list) {
            setNull(categoria);
        }
        return list;
    }

    private static void setNull(Categoria categoria) {
        categoria.setCodEmpresa(null);
        ControleProduto.limpaProduto(categoria.getProdutoList());
    }

    public static Categoria limpaCategoria(Categoria categoria) {
        setNull(categoria);
        return categoria;
    }

    public static int gravar(int cod, String descricao,int codEmpresa) {
        Categoria categoria = busca(cod);
        if (categoria == null) {
            categoria = new Categoria();
        }
        categoria.setCatDescricao(descricao);
        categoria.setCodEmpresa(ControleEmpresa.busca(codEmpresa));
        dao.gravar(categoria);
        return categoria.getCodCategoria();
    }

    public static Categoria busca(int cod) {
        return (Categoria) dao.busca(cod);
    }

    public static List<Categoria> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Categoria categoria = busca(cod);
        if (categoria == null) {
            return false;
        }
        dao.excluir(categoria);
        return true;
    }


}
