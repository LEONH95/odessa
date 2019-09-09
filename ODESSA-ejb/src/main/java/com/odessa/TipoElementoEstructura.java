/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odessa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CDIS 2019
 */
@Entity
@Table(name = "TipoElementoEstructura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoElementoEstructura.findAll", query = "SELECT t FROM TipoElementoEstructura t")
    , @NamedQuery(name = "TipoElementoEstructura.findByIdTipoElementoEstructura", query = "SELECT t FROM TipoElementoEstructura t WHERE t.idTipoElementoEstructura = :idTipoElementoEstructura")
    , @NamedQuery(name = "TipoElementoEstructura.findByNombre", query = "SELECT t FROM TipoElementoEstructura t WHERE t.nombre = :nombre")})
public class TipoElementoEstructura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoElementoEstructura")
    private Integer idTipoElementoEstructura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;

    public TipoElementoEstructura() {
    }

    public TipoElementoEstructura(Integer idTipoElementoEstructura) {
        this.idTipoElementoEstructura = idTipoElementoEstructura;
    }

    public TipoElementoEstructura(Integer idTipoElementoEstructura, String nombre) {
        this.idTipoElementoEstructura = idTipoElementoEstructura;
        this.nombre = nombre;
    }

    public Integer getIdTipoElementoEstructura() {
        return idTipoElementoEstructura;
    }

    public void setIdTipoElementoEstructura(Integer idTipoElementoEstructura) {
        this.idTipoElementoEstructura = idTipoElementoEstructura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoElementoEstructura != null ? idTipoElementoEstructura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoElementoEstructura)) {
            return false;
        }
        TipoElementoEstructura other = (TipoElementoEstructura) object;
        if ((this.idTipoElementoEstructura == null && other.idTipoElementoEstructura != null) || (this.idTipoElementoEstructura != null && !this.idTipoElementoEstructura.equals(other.idTipoElementoEstructura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odessa.TipoElementoEstructura[ idTipoElementoEstructura=" + idTipoElementoEstructura + " ]";
    }
    
}
