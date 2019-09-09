/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odessa;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CDIS 2019
 */
@Entity
@Table(name = "ClienteEstructura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteEstructura.findAll", query = "SELECT c FROM ClienteEstructura c")
    , @NamedQuery(name = "ClienteEstructura.findByIdClienteEstructura", query = "SELECT c FROM ClienteEstructura c WHERE c.idClienteEstructura = :idClienteEstructura")})
public class ClienteEstructura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClienteEstructura")
    private Integer idClienteEstructura;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteEstructura")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "idElementoEstructura", referencedColumnName = "idElementoEstructura")
    @ManyToOne(optional = false)
    private ElementoEstructura idElementoEstructura;

    public ClienteEstructura() {
    }

    public ClienteEstructura(Integer idClienteEstructura) {
        this.idClienteEstructura = idClienteEstructura;
    }

    public Integer getIdClienteEstructura() {
        return idClienteEstructura;
    }

    public void setIdClienteEstructura(Integer idClienteEstructura) {
        this.idClienteEstructura = idClienteEstructura;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public ElementoEstructura getIdElementoEstructura() {
        return idElementoEstructura;
    }

    public void setIdElementoEstructura(ElementoEstructura idElementoEstructura) {
        this.idElementoEstructura = idElementoEstructura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClienteEstructura != null ? idClienteEstructura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteEstructura)) {
            return false;
        }
        ClienteEstructura other = (ClienteEstructura) object;
        if ((this.idClienteEstructura == null && other.idClienteEstructura != null) || (this.idClienteEstructura != null && !this.idClienteEstructura.equals(other.idClienteEstructura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odessa.ClienteEstructura[ idClienteEstructura=" + idClienteEstructura + " ]";
    }
    
}
