/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
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

    public static int gravar(int cod, String razaoSocial, String nomeFantasia, String ie, String numero, int codEmpresa) {
        Filial filial = busca(cod);
        if (filial == null) {
            filial = new Filial();
        }
        Empresa empresa = ControleEmpresa.busca(codEmpresa);
        filial.setCodFilial(cod);
        filial.setFilIE(ie);
        filial.setFilRazaoSocial(razaoSocial);
        filial.setFilNomeFantasia(nomeFantasia);
        filial.setFilNumero(numero);
        filial.setCodEmpresa(empresa);
        dao.gravar(filial);
        return filial.getCodFilial();
    }

    public static Filial busca(int cod) {
        return (Filial) dao.busca(cod);
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

}
