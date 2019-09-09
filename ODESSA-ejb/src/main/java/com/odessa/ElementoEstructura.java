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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CDIS 2019
 */
@Entity
@Table(name = "ElementoEstructura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementoEstructura.findAll", query = "SELECT e FROM ElementoEstructura e")
    , @NamedQuery(name = "ElementoEstructura.findByIdElementoEstructura", query = "SELECT e FROM ElementoEstructura e WHERE e.idElementoEstructura = :idElementoEstructura")
    , @NamedQuery(name = "ElementoEstructura.findByNombre", query = "SELECT e FROM ElementoEstructura e WHERE e.nombre = :nombre")})
public class ElementoEstructura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idElementoEstructura")
    private Integer idElementoEstructura;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElementoEstructura")
    private List<ClienteEstructura> clienteEstructuraList;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Cliente idCliente;

    public ElementoEstructura() {
    }

    public ElementoEstructura(Integer idElementoEstructura) {
        this.idElementoEstructura = idElementoEstructura;
    }

    public Integer getIdElementoEstructura() {
        return idElementoEstructura;
    }

    public void setIdElementoEstructura(Integer idElementoEstructura) {
        this.idElementoEstructura = idElementoEstructura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ClienteEstructura> getClienteEstructuraList() {
        return clienteEstructuraList;
    }

    public void setClienteEstructuraList(List<ClienteEstructura> clienteEstructuraList) {
        this.clienteEstructuraList = clienteEstructuraList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElementoEstructura != null ? idElementoEstructura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementoEstructura)) {
            return false;
        }
        ElementoEstructura other = (ElementoEstructura) object;
        if ((this.idElementoEstructura == null && other.idElementoEstructura != null) || (this.idElementoEstructura != null && !this.idElementoEstructura.equals(other.idElementoEstructura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odessa.ElementoEstructura[ idElementoEstructura=" + idElementoEstructura + " ]";
    }
    
}
