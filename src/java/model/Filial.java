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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "filial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filial.findAll", query = "SELECT f FROM Filial f"),
    @NamedQuery(name = "Filial.findByCodFilial", query = "SELECT f FROM Filial f WHERE f.codFilial = :codFilial"),
    @NamedQuery(name = "Filial.findByCodEmpresa", query = "SELECT f FROM Filial f WHERE f.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "Filial.findByFilAtiva", query = "SELECT f FROM Filial f WHERE f.filAtiva = :filAtiva"),
    @NamedQuery(name = "Filial.findByFilCNPJ", query = "SELECT f FROM Filial f WHERE f.filCNPJ = :filCNPJ"),
    @NamedQuery(name = "Filial.findByFilIE", query = "SELECT f FROM Filial f WHERE f.filIE = :filIE"),
    @NamedQuery(name = "Filial.findByFilNomeFantasia", query = "SELECT f FROM Filial f WHERE f.filNomeFantasia = :filNomeFantasia"),
    @NamedQuery(name = "Filial.findByFilNumero", query = "SELECT f FROM Filial f WHERE f.filNumero = :filNumero"),
    @NamedQuery(name = "Filial.findByFilRazaoSocial", query = "SELECT f FROM Filial f WHERE f.filRazaoSocial = :filRazaoSocial")})
public class Filial implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFilial")
    private List<Venda> vendaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFilial")
    private List<Pedido> pedidoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Filial")
    private Integer codFilial;
    @Column(name = "filAtiva")
    private Boolean filAtiva;
    @Size(max = 255)
    @Column(name = "filCNPJ")
    private String filCNPJ;
    @Size(max = 255)
    @Column(name = "filIE")
    private String filIE;
    @Size(max = 255)
    @Column(name = "filNome_Fantasia")
    private String filNomeFantasia;
    @Size(max = 255)
    @Column(name = "filNumero")
    private String filNumero;
    @Size(max = 255)
    @Column(name = "filRazao_Social")
    private String filRazaoSocial;
    @JoinColumn(name = "Cod_Empresa", referencedColumnName = "Cod_Empresa")
    @ManyToOne
    private Empresa codEmpresa;
    @OneToMany(mappedBy = "codFilial")
    private List<Produto> produtoList;

    public Filial() {
    }

    public Filial(Integer codFilial) {
        this.codFilial = codFilial;
    }

    public Integer getCodFilial() {
        return codFilial;
    }

    public void setCodFilial(Integer codFilial) {
        this.codFilial = codFilial;
    }

    public Boolean getFilAtiva() {
        return filAtiva;
    }

    public void setFilAtiva(Boolean filAtiva) {
        this.filAtiva = filAtiva;
    }

    public String getFilCNPJ() {
        return filCNPJ;
    }

    public void setFilCNPJ(String filCNPJ) {
        this.filCNPJ = filCNPJ;
    }

    public String getFilIE() {
        return filIE;
    }

    public void setFilIE(String filIE) {
        this.filIE = filIE;
    }

    public String getFilNomeFantasia() {
        return filNomeFantasia;
    }

    public void setFilNomeFantasia(String filNomeFantasia) {
        this.filNomeFantasia = filNomeFantasia;
    }

    public String getFilNumero() {
        return filNumero;
    }

    public void setFilNumero(String filNumero) {
        this.filNumero = filNumero;
    }

    public String getFilRazaoSocial() {
        return filRazaoSocial;
    }

    public void setFilRazaoSocial(String filRazaoSocial) {
        this.filRazaoSocial = filRazaoSocial;
    }

    public Empresa getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Empresa codEmpresa) {
        this.codEmpresa = codEmpresa;
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
        hash += (codFilial != null ? codFilial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filial)) {
            return false;
        }
        Filial other = (Filial) object;
        if ((this.codFilial == null && other.codFilial != null) || (this.codFilial != null && !this.codFilial.equals(other.codFilial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.dao.Filial[ codFilial=" + codFilial + " ]";
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
    }

}
