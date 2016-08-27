/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT m FROM Marca m"),
    @NamedQuery(name = "Marca.findByCodMarca", query = "SELECT m FROM Marca m WHERE m.codMarca = :codMarca"),
    @NamedQuery(name = "Marca.findByMarDescricao", query = "SELECT m FROM Marca m WHERE m.marDescricao = :marDescricao")})
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Marca")
    private Integer codMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "marDescricao")
    private String marDescricao;
    @JoinColumn(name = "Cod_Empresa", referencedColumnName = "Cod_Empresa")
    @ManyToOne(optional = false)
    private Empresa codEmpresa;

    public Marca() {
    }

    public Marca(Integer codMarca) {
        this.codMarca = codMarca;
    }

    public Marca(Integer codMarca, String marDescricao) {
        this.codMarca = codMarca;
        this.marDescricao = marDescricao;
    }

    public Integer getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Integer codMarca) {
        this.codMarca = codMarca;
    }

    public String getMarDescricao() {
        return marDescricao;
    }

    public void setMarDescricao(String marDescricao) {
        this.marDescricao = marDescricao;
    }

    public Empresa getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Empresa codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMarca != null ? codMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.codMarca == null && other.codMarca != null) || (this.codMarca != null && !this.codMarca.equals(other.codMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Marca[ codMarca=" + codMarca + " ]";
    }
    
}
