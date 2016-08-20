/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByCodPedido", query = "SELECT p FROM Pedido p WHERE p.codPedido = :codPedido"),
    @NamedQuery(name = "Pedido.findByCodTipoPedido", query = "SELECT p FROM Pedido p WHERE p.codTipoPedido = :codTipoPedido"),
    @NamedQuery(name = "Pedido.findByPedDtBaixa", query = "SELECT p FROM Pedido p WHERE p.pedDtBaixa = :pedDtBaixa"),
    @NamedQuery(name = "Pedido.findByPedDtRealizacao", query = "SELECT p FROM Pedido p WHERE p.pedDtRealizacao = :pedDtRealizacao"),
    @NamedQuery(name = "Pedido.findByPedStatus", query = "SELECT p FROM Pedido p WHERE p.pedStatus = :pedStatus")})
public class Pedido implements Serializable {

    @JoinColumn(name = "Cod_Filial", referencedColumnName = "Cod_Filial")
    @ManyToOne(optional = false)
    private Filial codFilial;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Pedido")
    private Integer codPedido;
    @Column(name = "Cod_Tipo_Pedido")
    private Short codTipoPedido;
    @Column(name = "pedDt_Baixa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDtBaixa;
    @Column(name = "pedDt_Realizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDtRealizacao;
    @Column(name = "pedStatus")
    private Short pedStatus;
    @JoinTable(name = "item_pedido", joinColumns = {
        @JoinColumn(name = "Cod_Pedido", referencedColumnName = "Cod_Pedido")}, inverseJoinColumns = {
        @JoinColumn(name = "Cod_Produto", referencedColumnName = "Cod_Produto")})
    @ManyToMany
    private List<Produto> produtoList;

    public Pedido() {
    }

    public Pedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Short getCodTipoPedido() {
        return codTipoPedido;
    }

    public void setCodTipoPedido(Short codTipoPedido) {
        this.codTipoPedido = codTipoPedido;
    }

    public Date getPedDtBaixa() {
        return pedDtBaixa;
    }

    public void setPedDtBaixa(Date pedDtBaixa) {
        this.pedDtBaixa = pedDtBaixa;
    }

    public Date getPedDtRealizacao() {
        return pedDtRealizacao;
    }

    public void setPedDtRealizacao(Date pedDtRealizacao) {
        this.pedDtRealizacao = pedDtRealizacao;
    }

    public Short getPedStatus() {
        return pedStatus;
    }

    public void setPedStatus(Short pedStatus) {
        this.pedStatus = pedStatus;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPedido != null ? codPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.codPedido == null && other.codPedido != null) || (this.codPedido != null && !this.codPedido.equals(other.codPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.dao.Pedido[ codPedido=" + codPedido + " ]";
    }

    public Filial getCodFilial() {
        return codFilial;
    }

    public void setCodFilial(Filial codFilial) {
        this.codFilial = codFilial;
    }
    
}
