/* oooi
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Categoria;
import model.Filial;
import model.Marca;
import model.Produto;
import model.dao.DAO;
import model.dao.JpaProdutosDAO;

/**
 *
 * @author mauri
 */
public abstract class ControleProduto {
    
    static DAO dao = new JpaProdutosDAO();

    
    public static List<Produto> limpaProduto(List<Produto> list) {
        for (Produto produto : list) {
            setNull(produto);
        }
        return list;
    }

    private static void setNull(Produto produto) {
        produto.setPedidoList(null);
        ControleFilial.limpaFilial(produto.getCodFilial());
        ControleCategoria.limpaCategoria(produto.getCodCategoria());
        ControleMarca.limpaMarca(produto.getCodMarca());
        
    }

    public static Produto limpaProduto(Produto produto) {
        setNull(produto);
        return produto;
    }

    public static int gravar(int codProduto, int codCategoria, int codFilial, int codMarca, String proDescricao, String proReferencia, String proUrlImagem, BigDecimal proPrecoVenda) {
        Produto produto = busca(codProduto);
        if (produto == null) {
            produto = new Produto();
        }
        produto.setCodCategoria(ControleCategoria.busca(codCategoria));
        produto.setCodFilial(ControleFilial.busca(codFilial));
        produto.setCodMarca(ControleMarca.busca(codMarca));
        produto.setCodProduto(codProduto);
        produto.setProDescricao(proDescricao);
        produto.setProPrecoVenda(proPrecoVenda);
        produto.setProReferencia(proReferencia);
        produto.setProUrlImagem(proUrlImagem);
        dao.gravar(produto);
        return produto.getCodProduto();
    }

    public static Produto busca(int cod) {
        return (Produto) dao.busca(cod);
    }

    public static List<Produto> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Produto produtos = busca(cod);
        if (produtos == null) {
            return false;
        }
        dao.excluir(produtos);
        return true;
    }

    public static List<Produto> buscaPorFilial(int codFilial) {
        Map<String, Filial> map = new HashMap<>();
        map.put("codFilial", ControleFilial.busca(codFilial));
        return dao.findByNamedQuery("Produto.findByCodFilial", map, 0);
    }
}
