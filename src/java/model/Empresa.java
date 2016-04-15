/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByCodEmpresa", query = "SELECT e FROM Empresa e WHERE e.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "Empresa.findByEmpCNPJ", query = "SELECT e FROM Empresa e WHERE e.empCNPJ = :empCNPJ")})
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Empresa")
    private Integer codEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "empCNPJ")
    private String empCNPJ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEmpresa")
    private List<Filial> filialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEmpresa")
    private List<Pessoa> pessoaList;

    public Empresa() {
    }

    public Empresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Empresa(Integer codEmpresa, String empCNPJ) {
        this.codEmpresa = codEmpresa;
        this.empCNPJ = empCNPJ;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getEmpCNPJ() {
        return empCNPJ;
    }

    public void setEmpCNPJ(String empCNPJ) {
        this.empCNPJ = empCNPJ;
    }

    @XmlTransient
    public List<Filial> getFilialList() {
        return filialList;
    }

    public void setFilialList(List<Filial> filialList) {
        this.filialList = filialList;
    }

    @XmlTransient
    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmpresa != null ? codEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.codEmpresa == null && other.codEmpresa != null) || (this.codEmpresa != null && !this.codEmpresa.equals(other.codEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}
