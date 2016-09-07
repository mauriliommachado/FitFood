/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v"),
    @NamedQuery(name = "Vendedor.findByCodPessoa", query = "SELECT v FROM Vendedor v WHERE v.codPessoa = :codPessoa"),
    @NamedQuery(name = "Vendedor.findByVenNumero", query = "SELECT v FROM Vendedor v WHERE v.venNumero = :venNumero"),
    @NamedQuery(name = "Vendedor.findByVenComissao", query = "SELECT v FROM Vendedor v WHERE v.venComissao = :venComissao")})
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Pessoa")
    private Integer codPessoa;
    @Size(max = 10)
    @Column(name = "venNumero")
    private String venNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "venComissao")
    private BigDecimal venComissao;
    @JoinColumn(name = "Cod_Pessoa", referencedColumnName = "Cod_Pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;
    @OneToMany(mappedBy = "codVendedor")
    private List<Venda> vendaList;

    public Vendedor() {
    }

    public Vendedor(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getVenNumero() {
        return venNumero;
    }

    public void setVenNumero(String venNumero) {
        this.venNumero = venNumero;
    }

    public BigDecimal getVenComissao() {
        return venComissao;
    }

    public void setVenComissao(BigDecimal venComissao) {
        this.venComissao = venComissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Vendedor[ codPessoa=" + codPessoa + " ]";
    }
    
}
