/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.dao.DAO;
import model.Endereco;
import model.dao.JpaEnderecoDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleEndereco {

    static DAO dao = new JpaEnderecoDAO();

    public static List<Endereco> limpaEndereco(List<Endereco> list) {
        for (Endereco endereco : list) {
            setNull(endereco);
        }
        return list;
    }

    private static void setNull(Endereco endereco) {
        endereco.setCodPessoa(null);
    }

    public static Endereco limpaEndereco(Endereco endereco) {
        setNull(endereco);
        return endereco;
    }

    public static int gravar(int cod,int codPessoa, String bairro, String cep, String cidade, String complemento, String logradouro, String numero, String referencia, String uf) {
        Endereco endereco = busca(cod);
        if (endereco == null) {
            endereco = new Endereco();
        }
        endereco.setCodPessoa(ControlePessoa.busca(codPessoa));
        endereco.setEndBairro(bairro);
        endereco.setEndCep(cep);
        endereco.setEndCidade(cidade);
        endereco.setEndComplemento(complemento);
        endereco.setEndLogradouro(logradouro);
        endereco.setEndNumero(numero);
        endereco.setEndReferencia(referencia);
        endereco.setEndUF(uf);
        dao.gravar(endereco);
        return endereco.getCodEndereco();
    }

    public static Endereco busca(int cod) {
        return (Endereco) dao.busca(cod);
    }

    public static List<Endereco> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Endereco endereco = busca(cod);
        if (endereco == null) {
            return false;
        }
        dao.excluir(endereco);
        return true;
    }


}
