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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByCodVenda", query = "SELECT v FROM Venda v WHERE v.codVenda = :codVenda"),
    @NamedQuery(name = "Venda.findByVenDtVenda", query = "SELECT v FROM Venda v WHERE v.venDtVenda = :venDtVenda"),
    @NamedQuery(name = "Venda.findByVenChaveNFCE", query = "SELECT v FROM Venda v WHERE v.venChaveNFCE = :venChaveNFCE"),
    @NamedQuery(name = "Venda.findByMovCancelado", query = "SELECT v FROM Venda v WHERE v.movCancelado = :movCancelado")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cod_Venda")
    private Integer codVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venDt_Venda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venDtVenda;
    @Size(max = 15)
    @Column(name = "venChave_NFCE")
    private String venChaveNFCE;
    @Lob
    @Size(max = 65535)
    @Column(name = "venXML_NFCE")
    private String venXMLNFCE;
    @Column(name = "movCancelado")
    private Boolean movCancelado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<VendaPgto> vendaPgtoList;
    @JoinColumn(name = "Cod_Filial", referencedColumnName = "Cod_Filial")
    @ManyToOne(optional = false)
    private Filial codFilial;
    @JoinColumn(name = "Cod_Vendedor", referencedColumnName = "Cod_Pessoa")
    @ManyToOne
    private Vendedor codVendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<ItemVenda> itemVendaList;

    public Venda() {
    }

    public Venda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public Venda(Integer codVenda, Date venDtVenda) {
        this.codVenda = codVenda;
        this.venDtVenda = venDtVenda;
    }

    public Integer getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public Date getVenDtVenda() {
        return venDtVenda;
    }

    public void setVenDtVenda(Date venDtVenda) {
        this.venDtVenda = venDtVenda;
    }

    public String getVenChaveNFCE() {
        return venChaveNFCE;
    }

    public void setVenChaveNFCE(String venChaveNFCE) {
        this.venChaveNFCE = venChaveNFCE;
    }

    public String getVenXMLNFCE() {
        return venXMLNFCE;
    }

    public void setVenXMLNFCE(String venXMLNFCE) {
        this.venXMLNFCE = venXMLNFCE;
    }

    public Boolean getMovCancelado() {
        return movCancelado;
    }

    public void setMovCancelado(Boolean movCancelado) {
        this.movCancelado = movCancelado;
    }

    @XmlTransient
    public List<VendaPgto> getVendaPgtoList() {
        return vendaPgtoList;
    }

    public void setVendaPgtoList(List<VendaPgto> vendaPgtoList) {
        this.vendaPgtoList = vendaPgtoList;
    }

    public Filial getCodFilial() {
        return codFilial;
    }

    public void setCodFilial(Filial codFilial) {
        this.codFilial = codFilial;
    }

    public Vendedor getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(Vendedor codVendedor) {
        this.codVendedor = codVendedor;
    }

    @XmlTransient
    public List<ItemVenda> getItemVendaList() {
        return itemVendaList;
    }

    public void setItemVendaList(List<ItemVenda> itemVendaList) {
        this.itemVendaList = itemVendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVenda != null ? codVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.codVenda == null && other.codVenda != null) || (this.codVenda != null && !this.codVenda.equals(other.codVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Venda[ codVenda=" + codVenda + " ]";
    }
    
}
