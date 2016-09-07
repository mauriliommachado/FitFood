/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Empresa;
import model.Endereco;
import model.Pessoa;
import model.dao.DAO;
import model.dao.JpaPessoaDAO;

/**
 *
 * @author mauri
 */
public abstract class ControlePessoa {

    static DAO dao = new JpaPessoaDAO();

    public static List<Pessoa> limpaPessoa(List<Pessoa> list) {
        for (Pessoa pessoa : list) {
            setNull(pessoa);
        }
        return list;
    }

    private static void setNull(Pessoa pessoa) {
        for (Endereco end : pessoa.getEnderecoList()) {
            end.setCodPessoa(null);
        }
        ControleEndereco.limpaEndereco(pessoa.getEnderecoList());
        ControleTelefone.limpaTelefone(pessoa.getTelefoneList());
        pessoa.setCodEmpresa(null);
    }

    public static Pessoa limpaPessoa(Pessoa pessoa) {
        setNull(pessoa);
        return pessoa;
    }

    public static int gravar(int codPessoa, int codEmpresa, int tipoPessoa, List<Endereco> listaEndereco,
            boolean pessoaAtiva, String cpf, Date dtCadastro, String email, boolean pessoaFisica, String nome, String senha, boolean sexo) {
        Pessoa pessoa = busca(codPessoa);
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        pessoa.setCodEmpresa(ControleEmpresa.busca(codEmpresa));
        pessoa.setCodTipoPessoa(tipoPessoa);
        pessoa.setPesAtivo(pessoaAtiva);
        pessoa.setPesCPF(cpf);
        pessoa.setPesDtCadastro(dtCadastro);
        pessoa.setPesEmail(email);
        pessoa.setPesFisica(pessoaFisica);
        pessoa.setPesNome(nome);
        pessoa.setPesSenha(senha);
        pessoa.setPesSexo(sexo);
        dao.gravar(pessoa);
        pessoa.setEnderecoList(new ArrayList<>());
        for (Endereco end : listaEndereco) {
            pessoa.getEnderecoList().add(ControleEndereco.busca(ControleEndereco.gravar(end.getCodEndereco(), pessoa.getCodPessoa(), end.getEndBairro(), end.getEndCep(), end.getEndCidade(), end.getEndComplemento(), end.getEndLogradouro(), end.getEndNumero(), end.getEndReferencia(), end.getEndUF())));
        }
        dao.gravar(pessoa);
        return pessoa.getCodPessoa();
    }

    public static Pessoa busca(int cod) {
        return (Pessoa) dao.busca(cod);
    }

    public static List<Pessoa> busca() {
        return dao.listarTodos();
    }

    public static boolean deleta(int cod) {
        Pessoa pessoa = busca(cod);
        if (pessoa == null) {
            return false;
        }
        dao.excluir(pessoa);
        return true;
    }

    public static List<Pessoa> buscaPorEmpresa(int codEmpresa) {
        Map<String, Empresa> map = new HashMap<>();
        map.put("codEmpresa", ControleEmpresa.busca(codEmpresa));
        return dao.findByNamedQuery("Pessoa.findByCodEmpresa", map, 0);
    }
    
    public static Pessoa buscaPorTelefone(String telNumero){
        Map<String, String> map = new HashMap<>();
        map.put("telNumero",telNumero);
        return (Pessoa)dao.findByNamedQuery("Pessoa.findByTelNum", map, 0).get(0);
    }
    

    public static Pessoa authentification(String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
