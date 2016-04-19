/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maurílio
 */
@Entity
@Table(name = "filial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filial.findAll", query = "SELECT f FROM Filial f"),
    @NamedQuery(name = "Filial.findByCodEmpresa", query = "SELECT f FROM Filial f WHERE f.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "Filial.findByCodFilial", query = "SELECT f FROM Filial f WHERE f.codFilial = :codFilial"),
    @NamedQuery(name = "Filial.findByFilNumero", query = "SELECT f FROM Filial f WHERE f.filNumero = :filNumero"),
    @NamedQuery(name = "Filial.findByFilRazaoSocial", query = "SELECT f FROM Filial f WHERE f.filRazaoSocial = :filRazaoSocial"),
    @NamedQuery(name = "Filial.findByFilNomeFantasia", query = "SELECT f FROM Filial f WHERE f.filNomeFantasia = :filNomeFantasia"),
    @NamedQuery(name = "Filial.findByFilIE", query = "SELECT f FROM Filial f WHERE f.filIE = :filIE")})
public class Filial implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "filAtiva")
    private boolean filAtiva;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Filial")
    private Integer codFilial;
    @Size(max = 16)
    @Column(name = "filNumero")
    private String filNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "filRazao_Social")
    private String filRazaoSocial;
    @Size(max = 60)
    @Column(name = "filNome_Fantasia")
    private String filNomeFantasia;
    @Size(max = 12)
    @Column(name = "filIE")
    private String filIE;
    @JoinColumn(name = "Cod_Empresa", referencedColumnName = "Cod_Empresa")
    @ManyToOne(optional = false)
    private Empresa codEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codFilial")
    private List<Produto> produtoList;

    public Filial() {
    }

    public Filial(Integer codFilial) {
        this.codFilial = codFilial;
    }

    public Filial(Integer codFilial, String filRazaoSocial) {
        this.codFilial = codFilial;
        this.filRazaoSocial = filRazaoSocial;
    }

    public Integer getCodFilial() {
        return codFilial;
    }

    public void setCodFilial(Integer codFilial) {
        this.codFilial = codFilial;
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

    public String getFilNomeFantasia() {
        return filNomeFantasia;
    }

    public void setFilNomeFantasia(String filNomeFantasia) {
        this.filNomeFantasia = filNomeFantasia;
    }

    public String getFilIE() {
        return filIE;
    }

    public void setFilIE(String filIE) {
        this.filIE = filIE;
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
        return new Gson().toJson(this);
    }

    public boolean getFilAtiva() {
        return filAtiva;
    }

    public void setFilAtiva(boolean filAtiva) {
        this.filAtiva = filAtiva;
    }
    
}
