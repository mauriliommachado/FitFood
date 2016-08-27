/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Empresa;
import model.dao.DAO;
import model.Filial;
import model.dao.JpaFilialDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleFilial {

    static DAO dao = new JpaFilialDAO();

    public static int gravar(int cod, String razaoSocial, String nomeFantasia, String ie, String numero, boolean ativo, String cnpj, int codEmpresa) {
        Filial filial = busca(cod);
        if (filial == null) {
            filial = new Filial();
        }
        filial.setCodEmpresa(ControleEmpresa.busca(codEmpresa));
        filial.setCodFilial(cod);
        filial.setFilIE(ie);
        filial.setFilRazaoSocial(razaoSocial);
        filial.setFilNomeFantasia(nomeFantasia);
        filial.setFilNumero(numero);
        filial.setFilAtiva(ativo);
        filial.setFilCNPJ(cnpj);
        dao.gravar(filial);
        return filial.getCodFilial();
    }

    public static Filial busca(int cod) {
        return (Filial) dao.busca(cod);
    }

    public static List<Filial> buscaPorEmpresa(int cod) {
        Map<String, Empresa> map = new HashMap<>();
        map.put("codEmpresa", ControleEmpresa.busca(cod));
        return dao.findByNamedQuery("Filial.findByCodEmpresa", map, 0);
    }

    public static List<Filial> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Filial filial = busca(cod);
        if (filial == null) {
            return false;
        }
        dao.excluir(filial);
        return true;
    }

    public static Filial limpaFilial(Filial filial) {
        setNull(filial);
        return filial;
    }

    public static List<Filial> limpaFilial(List<Filial> filial) {
        for (Filial fil : filial) {
            setNull(fil);
        }
        return filial;
    }

    private static void setNull(Filial filial) {
        try {
            filial.setProdutoList(null);
            filial.setPedidoList(null);
            if (filial.getCodEmpresa() != null) {
                ControleEmpresa.limpaEmpresa(filial.getCodEmpresa());
            }
        } catch (Exception ex) {

        }

    }

}
