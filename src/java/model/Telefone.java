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
 * @author MrPerez
 */
@Entity
@Table(name = "telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByCodTelefone", query = "SELECT t FROM Telefone t WHERE t.codTelefone = :codTelefone"),
    @NamedQuery(name = "Telefone.findByTelTipo", query = "SELECT t FROM Telefone t WHERE t.telTipo = :telTipo"),
    @NamedQuery(name = "Telefone.findByTelNumero", query = "SELECT t FROM Telefone t WHERE t.telNumero = :telNumero")})
public class Telefone implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "telTipo")
    private short telTipo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Telefone")
    private Integer codTelefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "telNumero")
    private String telNumero;
    @JoinColumn(name = "Cod_pessoa", referencedColumnName = "Cod_Pessoa")
    @ManyToOne(optional = false)
    private Pessoa codpessoa;

    public Telefone() {
    }

    public Telefone(Integer codTelefone) {
        this.codTelefone = codTelefone;
    }

    public Telefone(Integer codTelefone, short telTipo, String telNumero) {
        this.codTelefone = codTelefone;
        this.telTipo = telTipo;
        this.telNumero = telNumero;
    }

    public Integer getCodTelefone() {
        return codTelefone;
    }

    public void setCodTelefone(Integer codTelefone) {
        this.codTelefone = codTelefone;
    }


    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
    }

    public Pessoa getCodpessoa() {
        return codpessoa;
    }

    public void setCodpessoa(Pessoa codpessoa) {
        this.codpessoa = codpessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTelefone != null ? codTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.codTelefone == null && other.codTelefone != null) || (this.codTelefone != null && !this.codTelefone.equals(other.codTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Telefone[ codTelefone=" + codTelefone + " ]";
    }

    public short getTelTipo() {
        return telTipo;
    }

    public void setTelTipo(short telTipo) {
        this.telTipo = telTipo;
    }
    
}
