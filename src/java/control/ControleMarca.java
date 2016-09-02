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
import model.Marca;
import model.dao.JpaMarcaDAO;

/**
 *
 * @author Maurílio
 */
public abstract class ControleMarca {

    static DAO dao = new JpaMarcaDAO();

    public static List<Marca> limpaMarca(List<Marca> list) {
        for (Marca marca : list) {
            setNull(marca);
        }
        return list;
    }

    private static void setNull(Marca marca) {
        marca.setCodEmpresa(null);
    }

    public static Marca limpaMarca(Marca marca) {
        setNull(marca);
        return marca;
    }

    public static int gravar(int cod, String descricao, int codEmpresa) {
        Marca marca = busca(cod);
        if (marca == null) {
            marca = new Marca();
        }
        marca.setMarDescricao(descricao);
        marca.setCodEmpresa(ControleEmpresa.busca(codEmpresa));
        dao.gravar(marca);
        return marca.getCodMarca();
    }

    public static Marca busca(int cod) {
        return (Marca) dao.busca(cod);
    }

    public static List<Marca> busca() {
        return dao.listarTodos();
    }

    public static List<Marca> buscaPorEmpresa(int cod) {
        Map<String, Empresa> map = new HashMap<>();
        map.put("codEmpresa", ControleEmpresa.busca(cod));
        return dao.findByNamedQuery("Marca.findByCodEmpresa", map, 0);
    }

    public static boolean deleta(int cod) {
        Marca marca = busca(cod);
        if (marca == null) {
            return false;
        }
        dao.excluir(marca);
        return true;
    }

}
