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
@Table(name = "item_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i"),
    @NamedQuery(name = "ItemVenda.findByCodvenda", query = "SELECT i FROM ItemVenda i WHERE i.itemVendaPK.codvenda = :codvenda"),
    @NamedQuery(name = "ItemVenda.findByCodProduto", query = "SELECT i FROM ItemVenda i WHERE i.itemVendaPK.codProduto = :codProduto"),
    @NamedQuery(name = "ItemVenda.findByItvValor", query = "SELECT i FROM ItemVenda i WHERE i.itvValor = :itvValor"),
    @NamedQuery(name = "ItemVenda.findByItvDescAcresc", query = "SELECT i FROM ItemVenda i WHERE i.itvDescAcresc = :itvDescAcresc"),
    @NamedQuery(name = "ItemVenda.findByItvQtde", query = "SELECT i FROM ItemVenda i WHERE i.itvQtde = :itvQtde"),
    @NamedQuery(name = "ItemVenda.findByItvCancelado", query = "SELECT i FROM ItemVenda i WHERE i.itvCancelado = :itvCancelado")})
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemVendaPK itemVendaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "itvValor")
    private BigDecimal itvValor;
    @Column(name = "itvDescAcresc")
    private BigDecimal itvDescAcresc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itvQtde")
    private int itvQtde;
    @Column(name = "itvCancelado")
    private Boolean itvCancelado;
    @JoinColumn(name = "Cod_venda", referencedColumnName = "Cod_Venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;
    @JoinColumn(name = "Cod_Produto", referencedColumnName = "Cod_Produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(ItemVendaPK itemVendaPK) {
        this.itemVendaPK = itemVendaPK;
    }

    public ItemVenda(ItemVendaPK itemVendaPK, int itvQtde) {
        this.itemVendaPK = itemVendaPK;
        this.itvQtde = itvQtde;
    }

    public ItemVenda(int codvenda, int codProduto) {
        this.itemVendaPK = new ItemVendaPK(codvenda, codProduto);
    }

    public ItemVendaPK getItemVendaPK() {
        return itemVendaPK;
    }

    public void setItemVendaPK(ItemVendaPK itemVendaPK) {
        this.itemVendaPK = itemVendaPK;
    }

    public BigDecimal getItvValor() {
        return itvValor;
    }

    public void setItvValor(BigDecimal itvValor) {
        this.itvValor = itvValor;
    }

    public BigDecimal getItvDescAcresc() {
        return itvDescAcresc;
    }

    public void setItvDescAcresc(BigDecimal itvDescAcresc) {
        this.itvDescAcresc = itvDescAcresc;
    }

    public int getItvQtde() {
        return itvQtde;
    }

    public void setItvQtde(int itvQtde) {
        this.itvQtde = itvQtde;
    }

    public Boolean getItvCancelado() {
        return itvCancelado;
    }

    public void setItvCancelado(Boolean itvCancelado) {
        this.itvCancelado = itvCancelado;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemVendaPK != null ? itemVendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        if ((this.itemVendaPK == null && other.itemVendaPK != null) || (this.itemVendaPK != null && !this.itemVendaPK.equals(other.itemVendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemVenda[ itemVendaPK=" + itemVendaPK + " ]";
    }
    
}
