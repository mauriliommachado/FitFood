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
@Table(name = "cfop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cfop.findAll", query = "SELECT c FROM Cfop c"),
    @NamedQuery(name = "Cfop.findByCodCFOP", query = "SELECT c FROM Cfop c WHERE c.codCFOP = :codCFOP"),
    @NamedQuery(name = "Cfop.findByCfpNumCFOP", query = "SELECT c FROM Cfop c WHERE c.cfpNumCFOP = :cfpNumCFOP"),
    @NamedQuery(name = "Cfop.findByCfpDescricao", query = "SELECT c FROM Cfop c WHERE c.cfpDescricao = :cfpDescricao")})
public class Cfop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_CFOP")
    private Integer codCFOP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cfpNum_CFOP")
    private String cfpNumCFOP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 65)
    @Column(name = "cfpDescricao")
    private String cfpDescricao;

    public Cfop() {
    }

    public Cfop(Integer codCFOP) {
        this.codCFOP = codCFOP;
    }

    public Cfop(Integer codCFOP, String cfpNumCFOP, String cfpDescricao) {
        this.codCFOP = codCFOP;
        this.cfpNumCFOP = cfpNumCFOP;
        this.cfpDescricao = cfpDescricao;
    }

    public Integer getCodCFOP() {
        return codCFOP;
    }

    public void setCodCFOP(Integer codCFOP) {
        this.codCFOP = codCFOP;
    }

    public String getCfpNumCFOP() {
        return cfpNumCFOP;
    }

    public void setCfpNumCFOP(String cfpNumCFOP) {
        this.cfpNumCFOP = cfpNumCFOP;
    }

    public String getCfpDescricao() {
        return cfpDescricao;
    }

    public void setCfpDescricao(String cfpDescricao) {
        this.cfpDescricao = cfpDescricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCFOP != null ? codCFOP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cfop)) {
            return false;
        }
        Cfop other = (Cfop) object;
        if ((this.codCFOP == null && other.codCFOP != null) || (this.codCFOP != null && !this.codCFOP.equals(other.codCFOP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cfop[ codCFOP=" + codCFOP + " ]";
    }
    
}
