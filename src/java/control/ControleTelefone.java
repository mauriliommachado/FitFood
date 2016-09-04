/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.dao.DAO;
import model.Telefone;
import model.Telefone_;
import model.dao.JpaTelefoneDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleTelefone {

    static DAO dao = new JpaTelefoneDAO();

    public static List<Telefone> limpaTelefone(List<Telefone> list) {
        for (Telefone telefone : list) {
            setNull(telefone);
        }
        return list;
    }

    private static void setNull(Telefone telefone) {
        telefone.setCodpessoa(null);
    }

    public static Telefone limpaTelefone(Telefone telefone) {
        setNull(telefone);
        return telefone;
    }

    public static int gravar(int cod, String telNum, short telTipo, int codPessoa) {
        Telefone telefone = busca(cod);
        if (telefone == null) {
            telefone = new Telefone();
        }
        
        telefone.setTelNumero(telNum);
        telefone.setTelTipo(telTipo);
        telefone.setCodpessoa(ControlePessoa.busca(codPessoa));
        
        dao.gravar(telefone);
        return telefone.getCodTelefone();
    }

    public static Telefone busca(int cod) {
        return (Telefone) dao.busca(cod);
    }

    public static List<Telefone> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Telefone telefone = busca(cod);
        if (telefone == null) {
            return false;
        }
        dao.excluir(telefone);
        return true;
    }


}
