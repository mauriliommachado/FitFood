/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "forma_pgto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPgto.findAll", query = "SELECT f FROM FormaPgto f"),
    @NamedQuery(name = "FormaPgto.findByCodFormaPgto", query = "SELECT f FROM FormaPgto f WHERE f.codFormaPgto = :codFormaPgto"),
    @NamedQuery(name = "FormaPgto.findByFpgTipo", query = "SELECT f FROM FormaPgto f WHERE f.fpgTipo = :fpgTipo"),
    @NamedQuery(name = "FormaPgto.findByFpgDescricao", query = "SELECT f FROM FormaPgto f WHERE f.fpgDescricao = :fpgDescricao")})
public class FormaPgto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Forma_Pgto")
    private Integer codFormaPgto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fpgTipo")
    private int fpgTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "fpgDescricao")
    private String fpgDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPgto")
    private List<VendaPgto> vendaPgtoList;

    public FormaPgto() {
    }

    public FormaPgto(Integer codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public FormaPgto(Integer codFormaPgto, int fpgTipo, String fpgDescricao) {
        this.codFormaPgto = codFormaPgto;
        this.fpgTipo = fpgTipo;
        this.fpgDescricao = fpgDescricao;
    }

    public Integer getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(Integer codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public int getFpgTipo() {
        return fpgTipo;
    }

    public void setFpgTipo(int fpgTipo) {
        this.fpgTipo = fpgTipo;
    }

    public String getFpgDescricao() {
        return fpgDescricao;
    }

    public void setFpgDescricao(String fpgDescricao) {
        this.fpgDescricao = fpgDescricao;
    }

    @XmlTransient
    public List<VendaPgto> getVendaPgtoList() {
        return vendaPgtoList;
    }

    public void setVendaPgtoList(List<VendaPgto> vendaPgtoList) {
        this.vendaPgtoList = vendaPgtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFormaPgto != null ? codFormaPgto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPgto)) {
            return false;
        }
        FormaPgto other = (FormaPgto) object;
        if ((this.codFormaPgto == null && other.codFormaPgto != null) || (this.codFormaPgto != null && !this.codFormaPgto.equals(other.codFormaPgto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FormaPgto[ codFormaPgto=" + codFormaPgto + " ]";
    }
    
}
