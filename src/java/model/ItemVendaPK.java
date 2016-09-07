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
public class ItemVendaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_venda")
    private int codvenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Produto")
    private int codProduto;

    public ItemVendaPK() {
    }

    public ItemVendaPK(int codvenda, int codProduto) {
        this.codvenda = codvenda;
        this.codProduto = codProduto;
    }

    public int getCodvenda() {
        return codvenda;
    }

    public void setCodvenda(int codvenda) {
        this.codvenda = codvenda;
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
        hash += (int) codvenda;
        hash += (int) codProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemVendaPK)) {
            return false;
        }
        ItemVendaPK other = (ItemVendaPK) object;
        if (this.codvenda != other.codvenda) {
            return false;
        }
        if (this.codProduto != other.codProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ItemVendaPK[ codvenda=" + codvenda + ", codProduto=" + codProduto + " ]";
    }
    
}
