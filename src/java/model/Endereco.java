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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByCodEndereco", query = "SELECT e FROM Endereco e WHERE e.codEndereco = :codEndereco"),
    @NamedQuery(name = "Endereco.findByEndBairro", query = "SELECT e FROM Endereco e WHERE e.endBairro = :endBairro"),
    @NamedQuery(name = "Endereco.findByEndCep", query = "SELECT e FROM Endereco e WHERE e.endCep = :endCep"),
    @NamedQuery(name = "Endereco.findByEndCidade", query = "SELECT e FROM Endereco e WHERE e.endCidade = :endCidade"),
    @NamedQuery(name = "Endereco.findByEndComplemento", query = "SELECT e FROM Endereco e WHERE e.endComplemento = :endComplemento"),
    @NamedQuery(name = "Endereco.findByEndLogradouro", query = "SELECT e FROM Endereco e WHERE e.endLogradouro = :endLogradouro"),
    @NamedQuery(name = "Endereco.findByEndNumero", query = "SELECT e FROM Endereco e WHERE e.endNumero = :endNumero"),
    @NamedQuery(name = "Endereco.findByEndReferencia", query = "SELECT e FROM Endereco e WHERE e.endReferencia = :endReferencia"),
    @NamedQuery(name = "Endereco.findByEndUF", query = "SELECT e FROM Endereco e WHERE e.endUF = :endUF")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Endereco")
    private Integer codEndereco;
    @Size(max = 255)
    @Column(name = "endBairro")
    private String endBairro;
    @Size(max = 255)
    @Column(name = "endCep")
    private String endCep;
    @Size(max = 255)
    @Column(name = "endCidade")
    private String endCidade;
    @Size(max = 255)
    @Column(name = "endComplemento")
    private String endComplemento;
    @Size(max = 255)
    @Column(name = "endLogradouro")
    private String endLogradouro;
    @Size(max = 255)
    @Column(name = "endNumero")
    private String endNumero;
    @Size(max = 255)
    @Column(name = "endReferencia")
    private String endReferencia;
    @Size(max = 255)
    @Column(name = "endUF")
    private String endUF;
    @JoinColumn(name = "Cod_Pessoa", referencedColumnName = "Cod_Pessoa")
    @ManyToOne
    private Pessoa codPessoa;

    public Endereco() {
    }

    public Endereco(Integer codEndereco) {
        this.codEndereco = codEndereco;
    }

    public Integer getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Integer codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndComplemento() {
        return endComplemento;
    }

    public void setEndComplemento(String endComplemento) {
        this.endComplemento = endComplemento;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndReferencia() {
        return endReferencia;
    }

    public void setEndReferencia(String endReferencia) {
        this.endReferencia = endReferencia;
    }

    public String getEndUF() {
        return endUF;
    }

    public void setEndUF(String endUF) {
        this.endUF = endUF;
    }

    public Pessoa getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Pessoa codPessoa) {
        this.codPessoa = codPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEndereco != null ? codEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.codEndereco == null && other.codEndereco != null) || (this.codEndereco != null && !this.codEndereco.equals(other.codEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.dao.Endereco[ codEndereco=" + codEndereco + " ]";
    }
    
}
