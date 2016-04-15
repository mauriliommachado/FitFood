/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.dao.DAO;
import model.Empresa;
import model.dao.JpaEmpresasDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleEmpresa {

    static DAO dao = new JpaEmpresasDAO();

    public static int gravar(int cod, String cnpj) {
        Empresa empresa = busca(cod);
        if (empresa==null) {
            empresa=new Empresa();
        }
        empresa.setEmpCNPJ(cnpj);
        dao.gravar(empresa);
        return empresa.getCodEmpresa();
    }

    public static Empresa busca(int cod) {
        return (Empresa) dao.busca(cod);
    }

    public static List<Empresa> busca() {
        return dao.listarTodos();
    }
    
    public static boolean deleta(int cod){
        Empresa empresa = busca(cod);
        if (empresa==null ) {
            return false;
        }
        dao.excluir(empresa);
        return true;
    }

}
