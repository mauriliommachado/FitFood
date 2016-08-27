/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.dao.DAO;
import model.Pedido;
import model.Produto;
import model.dao.JpaPedidoDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControlePedido {

    static DAO dao = new JpaPedidoDAO();

    public static List<Pedido> limpaPedido(List<Pedido> list) {
        for (Pedido pedido : list) {
            setNull(pedido);
        }
        return list;
    }

    private static void setNull(Pedido pedido) {
        pedido.setCodFilial(null);
        ControleProduto.limpaProduto(pedido.getProdutoList());
    }

    public static Pedido limpaPedido(Pedido pedido) {
        setNull(pedido);
        return pedido;
    }

    public static int gravar(int cod, int codFilial, Date pedDtBaixa, Date pedDtRealizacao, List<Produto> produtos) {
        Pedido pedido = busca(cod);
        if (pedido == null) {
            pedido = new Pedido();
        }
        pedido.setCodFilial(ControleFilial.busca(codFilial));
        pedido.setCodTipoPedido(Short.MIN_VALUE);
        pedido.setPedDtBaixa(pedDtBaixa);
        pedido.setPedDtRealizacao(pedDtRealizacao);
        pedido.setPedStatus(Short.MIN_VALUE);
        pedido.setProdutoList(new ArrayList<>());
        for(Produto prod :produtos){
            pedido.getProdutoList().add(ControleProduto.busca(prod.getCodProduto()));
        }
        dao.gravar(pedido);
        return pedido.getCodPedido();
    }

    public static Pedido busca(int cod) {
        return (Pedido) dao.busca(cod);
    }

    public static List<Pedido> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Pedido pedido = busca(cod);
        if (pedido == null) {
            return false;
        }
        dao.excluir(pedido);
        return true;
    }

}
