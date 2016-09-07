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
@Table(name = "item_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i"),
    @NamedQuery(name = "ItemPedido.findByCodPedido", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.codPedido = :codPedido"),
    @NamedQuery(name = "ItemPedido.findByCodProduto", query = "SELECT i FROM ItemPedido i WHERE i.itemPedidoPK.codProduto = :codProduto"),
    @NamedQuery(name = "ItemPedido.findByItpQtde", query = "SELECT i FROM ItemPedido i WHERE i.itpQtde = :itpQtde"),
    @NamedQuery(name = "ItemPedido.findByItpValor", query = "SELECT i FROM ItemPedido i WHERE i.itpValor = :itpValor"),
    @NamedQuery(name = "ItemPedido.findByItpDescAcresc", query = "SELECT i FROM ItemPedido i WHERE i.itpDescAcresc = :itpDescAcresc")})
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPedidoPK itemPedidoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itpQtde")
    private int itpQtde;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "itpValor")
    private BigDecimal itpValor;
    @Column(name = "itpDescAcresc")
    private BigDecimal itpDescAcresc;
    @JoinColumn(name = "Cod_Pedido", referencedColumnName = "Cod_Pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "Cod_Produto", referencedColumnName = "Cod_Produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public ItemPedido(ItemPedidoPK itemPedidoPK, int itpQtde, BigDecimal itpValor) {
        this.itemPedidoPK = itemPedidoPK;
        this.itpQtde = itpQtde;
        this.itpValor = itpValor;
    }

    public ItemPedido(int codPedido, int codProduto) {
        this.itemPedidoPK = new ItemPedidoPK(codPedido, codProduto);
    }

    public ItemPedidoPK getItemPedidoPK() {
        return itemPedidoPK;
    }

    public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public int getItpQtde() {
        return itpQtde;
    }

    public void setItpQtde(int itpQtde) {
        this.itpQtde = itpQtde;
    }

    public BigDecimal getItpValor() {
        return itpValor;
    }

    public void setItpValor(BigDecimal itpValor) {
        this.itpValor = itpValor;
    }

    public BigDecimal getItpDescAcresc() {
        return itpDescAcresc;
    }

    public void setItpDescAcresc(BigDecimal itpDescAcresc) {
        this.itpDescAcresc = itpDescAcresc;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        hash += (itemPedidoPK != null ? itemPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.itemPedidoPK == null && other.itemPedidoPK != null) || (this.itemPedidoPK != null && !this.itemPedidoPK.equals(other.itemPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemPedido[ itemPedidoPK=" + itemPedidoPK + " ]";
    }
    
}
