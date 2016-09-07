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
public class VendaPgtoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Forma_Pgto")
    private int codFormaPgto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Venda")
    private int codVenda;

    public VendaPgtoPK() {
    }

    public VendaPgtoPK(int codFormaPgto, int codVenda) {
        this.codFormaPgto = codFormaPgto;
        this.codVenda = codVenda;
    }

    public int getCodFormaPgto() {
        return codFormaPgto;
    }

    public void setCodFormaPgto(int codFormaPgto) {
        this.codFormaPgto = codFormaPgto;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codFormaPgto;
        hash += (int) codVenda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaPgtoPK)) {
            return false;
        }
        VendaPgtoPK other = (VendaPgtoPK) object;
        if (this.codFormaPgto != other.codFormaPgto) {
            return false;
        }
        if (this.codVenda != other.codVenda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.VendaPgtoPK[ codFormaPgto=" + codFormaPgto + ", codVenda=" + codVenda + " ]";
    }
    
}
