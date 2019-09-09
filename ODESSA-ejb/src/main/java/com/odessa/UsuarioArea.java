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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CDIS 2019
 */
@Entity
@Table(name = "UsuarioArea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioArea.findAll", query = "SELECT u FROM UsuarioArea u")
    , @NamedQuery(name = "UsuarioArea.findByIdUsuarioArea", query = "SELECT u FROM UsuarioArea u WHERE u.idUsuarioArea = :idUsuarioArea")})
public class UsuarioArea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarioArea")
    private Integer idUsuarioArea;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public UsuarioArea() {
    }

    public UsuarioArea(Integer idUsuarioArea) {
        this.idUsuarioArea = idUsuarioArea;
    }
    

    public Integer getIdUsuarioArea() {
        return idUsuarioArea;
    }

    public void setIdUsuarioArea(Integer idUsuarioArea) {
        this.idUsuarioArea = idUsuarioArea;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioArea != null ? idUsuarioArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioArea)) {
            return false;
        }
        UsuarioArea other = (UsuarioArea) object;
        if ((this.idUsuarioArea == null && other.idUsuarioArea != null) || (this.idUsuarioArea != null && !this.idUsuarioArea.equals(other.idUsuarioArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odessa.UsuarioArea[ idUsuarioArea=" + idUsuarioArea + " ]";
    }
    
}
