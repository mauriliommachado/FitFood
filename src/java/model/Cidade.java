/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByCodCidade", query = "SELECT c FROM Cidade c WHERE c.codCidade = :codCidade"),
    @NamedQuery(name = "Cidade.findByCodUF", query = "SELECT c FROM Cidade c WHERE c.codUF = :codUF"),
    @NamedQuery(name = "Cidade.findByCidDescricao", query = "SELECT c FROM Cidade c WHERE c.cidDescricao = :cidDescricao"),
    @NamedQuery(name = "Cidade.findByCidCodIBGE", query = "SELECT c FROM Cidade c WHERE c.cidCodIBGE = :cidCodIBGE")})
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Cidade")
    private Integer codCidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_UF")
    private int codUF;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cidDescricao")
    private String cidDescricao;
    @Size(max = 7)
    @Column(name = "cidCodIBGE")
    private String cidCodIBGE;

    public Cidade() {
    }

    public Cidade(Integer codCidade) {
        this.codCidade = codCidade;
    }

    public Cidade(Integer codCidade, int codUF, String cidDescricao) {
        this.codCidade = codCidade;
        this.codUF = codUF;
        this.cidDescricao = cidDescricao;
    }

    public Integer getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Integer codCidade) {
        this.codCidade = codCidade;
    }

    public int getCodUF() {
        return codUF;
    }

    public void setCodUF(int codUF) {
        this.codUF = codUF;
    }

    public String getCidDescricao() {
        return cidDescricao;
    }

    public void setCidDescricao(String cidDescricao) {
        this.cidDescricao = cidDescricao;
    }

    public String getCidCodIBGE() {
        return cidCodIBGE;
    }

    public void setCidCodIBGE(String cidCodIBGE) {
        this.cidCodIBGE = cidCodIBGE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCidade != null ? codCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.codCidade == null && other.codCidade != null) || (this.codCidade != null && !this.codCidade.equals(other.codCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cidade[ codCidade=" + codCidade + " ]";
    }
    
}
