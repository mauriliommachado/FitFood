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
@Table(name = "uf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uf.findAll", query = "SELECT u FROM Uf u"),
    @NamedQuery(name = "Uf.findByCodUF", query = "SELECT u FROM Uf u WHERE u.codUF = :codUF"),
    @NamedQuery(name = "Uf.findByUfSigla", query = "SELECT u FROM Uf u WHERE u.ufSigla = :ufSigla"),
    @NamedQuery(name = "Uf.findByUfDescricao", query = "SELECT u FROM Uf u WHERE u.ufDescricao = :ufDescricao")})
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_UF")
    private Integer codUF;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ufSigla")
    private String ufSigla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "ufDescricao")
    private String ufDescricao;

    public Uf() {
    }

    public Uf(Integer codUF) {
        this.codUF = codUF;
    }

    public Uf(Integer codUF, String ufSigla, String ufDescricao) {
        this.codUF = codUF;
        this.ufSigla = ufSigla;
        this.ufDescricao = ufDescricao;
    }

    public Integer getCodUF() {
        return codUF;
    }

    public void setCodUF(Integer codUF) {
        this.codUF = codUF;
    }

    public String getUfSigla() {
        return ufSigla;
    }

    public void setUfSigla(String ufSigla) {
        this.ufSigla = ufSigla;
    }

    public String getUfDescricao() {
        return ufDescricao;
    }

    public void setUfDescricao(String ufDescricao) {
        this.ufDescricao = ufDescricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUF != null ? codUF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uf)) {
            return false;
        }
        Uf other = (Uf) object;
        if ((this.codUF == null && other.codUF != null) || (this.codUF != null && !this.codUF.equals(other.codUF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Uf[ codUF=" + codUF + " ]";
    }
    
}
