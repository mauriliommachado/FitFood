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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mauri
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByCodPessoa", query = "SELECT p FROM Pessoa p WHERE p.codPessoa = :codPessoa"),
    @NamedQuery(name = "Pessoa.findByCodEmpresa", query = "SELECT p FROM Pessoa p WHERE p.codEmpresa = :codEmpresa"),
    @NamedQuery(name = "Pessoa.findByCodTipoPessoa", query = "SELECT p FROM Pessoa p WHERE p.codTipoPessoa = :codTipoPessoa"),
    @NamedQuery(name = "Pessoa.findByPesAtivo", query = "SELECT p FROM Pessoa p WHERE p.pesAtivo = :pesAtivo"),
    @NamedQuery(name = "Pessoa.findByPesCPF", query = "SELECT p FROM Pessoa p WHERE p.pesCPF = :pesCPF"),
    @NamedQuery(name = "Pessoa.findByPesDtCadastro", query = "SELECT p FROM Pessoa p WHERE p.pesDtCadastro = :pesDtCadastro"),
    @NamedQuery(name = "Pessoa.findByPesEmail", query = "SELECT p FROM Pessoa p WHERE p.pesEmail = :pesEmail"),
    @NamedQuery(name = "Pessoa.findByPesFisica", query = "SELECT p FROM Pessoa p WHERE p.pesFisica = :pesFisica"),
    @NamedQuery(name = "Pessoa.findByPesNome", query = "SELECT p FROM Pessoa p WHERE p.pesNome = :pesNome"),
    @NamedQuery(name = "Pessoa.findByPesSenha", query = "SELECT p FROM Pessoa p WHERE p.pesSenha = :pesSenha"),
    @NamedQuery(name = "Pessoa.findByPesSexo", query = "SELECT p FROM Pessoa p WHERE p.pesSexo = :pesSexo")})
public class Pessoa implements Serializable {

    @Size(max = 11)
    @Column(name = "pesCPF")
    private String pesCPF;

    @Column(name = "Cod_Tipo_Pessoa")
    private Integer codTipoPessoa;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cod_Pessoa")
    private Integer codPessoa;
    @Column(name = "pesAtivo")
    private Boolean pesAtivo;
    @Column(name = "pesDtCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pesDtCadastro;
    @Size(max = 255)
    @Column(name = "pesEmail")
    private String pesEmail;
    @Column(name = "pesFisica")
    private Boolean pesFisica;
    @Size(max = 255)
    @Column(name = "pesNome")
    private String pesNome;
    @Size(max = 255)
    @Column(name = "pesSenha")
    private String pesSenha;
    @Column(name = "pesSexo")
    private Boolean pesSexo;
    @JoinColumn(name = "Cod_Empresa", referencedColumnName = "Cod_Empresa")
    @ManyToOne
    private Empresa codEmpresa;
    @OneToMany(mappedBy = "codPessoa")
    private List<Endereco> enderecoList;

    public Pessoa() {
    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public Boolean getPesAtivo() {
        return pesAtivo;
    }

    public void setPesAtivo(Boolean pesAtivo) {
        this.pesAtivo = pesAtivo;
    }


    public Date getPesDtCadastro() {
        return pesDtCadastro;
    }

    public void setPesDtCadastro(Date pesDtCadastro) {
        this.pesDtCadastro = pesDtCadastro;
    }

    public String getPesEmail() {
        return pesEmail;
    }

    public void setPesEmail(String pesEmail) {
        this.pesEmail = pesEmail;
    }

    public Boolean getPesFisica() {
        return pesFisica;
    }

    public void setPesFisica(Boolean pesFisica) {
        this.pesFisica = pesFisica;
    }

    public String getPesNome() {
        return pesNome;
    }

    public void setPesNome(String pesNome) {
        this.pesNome = pesNome;
    }

    public String getPesSenha() {
        return pesSenha;
    }

    public void setPesSenha(String pesSenha) {
        this.pesSenha = pesSenha;
    }

    public Boolean getPesSexo() {
        return pesSexo;
    }

    public void setPesSexo(Boolean pesSexo) {
        this.pesSexo = pesSexo;
    }

    public Empresa getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Empresa codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    @XmlTransient
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.codPessoa == null && other.codPessoa != null) || (this.codPessoa != null && !this.codPessoa.equals(other.codPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.dao.Pessoa[ codPessoa=" + codPessoa + " ]";
    }

    public Integer getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(Integer codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    public String getPesCPF() {
        return pesCPF;
    }

    public void setPesCPF(String pesCPF) {
        this.pesCPF = pesCPF;
    }
    
}
