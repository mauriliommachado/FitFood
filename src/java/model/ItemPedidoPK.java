/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mauri
 */
@Embeddable
public class ItemPedidoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Pedido")
    private int codPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Produto")
    private int codProduto;

    public ItemPedidoPK() {
    }

    public ItemPedidoPK(int codPedido, int codProduto) {
        this.codPedido = codPedido;
        this.codProduto = codProduto;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPedido;
        hash += (int) codProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedidoPK)) {
            return false;
        }
        ItemPedidoPK other = (ItemPedidoPK) object;
        if (this.codPedido != other.codPedido) {
            return false;
        }
        if (this.codProduto != other.codProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemPedidoPK[ codPedido=" + codPedido + ", codProduto=" + codProduto + " ]";
    }
    
}
