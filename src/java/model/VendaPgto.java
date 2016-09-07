/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "venda_pgto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendaPgto.findAll", query = "SELECT v FROM VendaPgto v"),
    @NamedQuery(name = "VendaPgto.findByCodFormaPgto", query = "SELECT v FROM VendaPgto v WHERE v.vendaPgtoPK.codFormaPgto = :codFormaPgto"),
    @NamedQuery(name = "VendaPgto.findByCodVenda", query = "SELECT v FROM VendaPgto v WHERE v.vendaPgtoPK.codVenda = :codVenda"),
    @NamedQuery(name = "VendaPgto.findByVpgValor", query = "SELECT v FROM VendaPgto v WHERE v.vpgValor = :vpgValor"),
    @NamedQuery(name = "VendaPgto.findByVpgDescAcresc", query = "SELECT v FROM VendaPgto v WHERE v.vpgDescAcresc = :vpgDescAcresc")})
public class VendaPgto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaPgtoPK vendaPgtoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "vpgValor")
    private BigDecimal vpgValor;
    @Column(name = "vpgDescAcresc")
    private BigDecimal vpgDescAcresc;
    @JoinColumn(name = "Cod_Forma_Pgto", referencedColumnName = "Cod_Forma_Pgto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FormaPgto formaPgto;
    @JoinColumn(name = "Cod_Venda", referencedColumnName = "Cod_Venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public VendaPgto() {
    }

    public VendaPgto(VendaPgtoPK vendaPgtoPK) {
        this.vendaPgtoPK = vendaPgtoPK;
    }

    public VendaPgto(VendaPgtoPK vendaPgtoPK, BigDecimal vpgValor) {
        this.vendaPgtoPK = vendaPgtoPK;
        this.vpgValor = vpgValor;
    }

    public VendaPgto(int codFormaPgto, int codVenda) {
        this.vendaPgtoPK = new VendaPgtoPK(codFormaPgto, codVenda);
    }

    public VendaPgtoPK getVendaPgtoPK() {
        return vendaPgtoPK;
    }

    public void setVendaPgtoPK(VendaPgtoPK vendaPgtoPK) {
        this.vendaPgtoPK = vendaPgtoPK;
    }

    public BigDecimal getVpgValor() {
        return vpgValor;
    }

    public void setVpgValor(BigDecimal vpgValor) {
        this.vpgValor = vpgValor;
    }

    public BigDecimal getVpgDescAcresc() {
        return vpgDescAcresc;
    }

    public void setVpgDescAcresc(BigDecimal vpgDescAcresc) {
        this.vpgDescAcresc = vpgDescAcresc;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendaPgtoPK != null ? vendaPgtoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaPgto)) {
            return false;
        }
        VendaPgto other = (VendaPgto) object;
        if ((this.vendaPgtoPK == null && other.vendaPgtoPK != null) || (this.vendaPgtoPK != null && !this.vendaPgtoPK.equals(other.vendaPgtoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VendaPgto[ vendaPgtoPK=" + vendaPgtoPK + " ]";
    }
    
}
