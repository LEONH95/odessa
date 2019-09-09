/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odessa;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CDIS 2019
 */
@Entity
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNumeroTrabajador", query = "SELECT u FROM Usuario u WHERE u.numeroTrabajador = :numeroTrabajador")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidoPaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Usuario.findByApellidoMaterno", query = "SELECT u FROM Usuario u WHERE u.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByNumeroOficina", query = "SELECT u FROM Usuario u WHERE u.numeroOficina = :numeroOficina")
    , @NamedQuery(name = "Usuario.findByTelefonoOficina", query = "SELECT u FROM Usuario u WHERE u.telefonoOficina = :telefonoOficina")
    , @NamedQuery(name = "Usuario.findByTelefonoCasa", query = "SELECT u FROM Usuario u WHERE u.telefonoCasa = :telefonoCasa")
    , @NamedQuery(name = "Usuario.findByExtension", query = "SELECT u FROM Usuario u WHERE u.extension = :extension")
    , @NamedQuery(name = "Usuario.findByMovil1", query = "SELECT u FROM Usuario u WHERE u.movil1 = :movil1")
    , @NamedQuery(name = "Usuario.findByMovil2", query = "SELECT u FROM Usuario u WHERE u.movil2 = :movil2")
    , @NamedQuery(name = "Usuario.findByHabilitado", query = "SELECT u FROM Usuario u WHERE u.habilitado = :habilitado")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByNumeroUnico", query = "SELECT u FROM Usuario u WHERE u.numeroUnico = :numeroUnico")
    , @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "numeroTrabajador")
    private String numeroTrabajador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellidoPaterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "contrasena")
    private String contrasena;
    @Size(max = 11)
    @Column(name = "numeroOficina")
    private String numeroOficina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefonoOficina")
    private String telefonoOficina;
    @Size(max = 15)
    @Column(name = "telefonoCasa")
    private String telefonoCasa;
    @Size(max = 5)
    @Column(name = "extension")
    private String extension;
    @Size(max = 15)
    @Column(name = "movil1")
    private String movil1;
    @Size(max = 15)
    @Column(name = "movil2")
    private String movil2;
    @Column(name = "habilitado")
    private Boolean habilitado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Size(min = 1, max = 11)
    @Column(name = "numeroUnico")
    private String numeroUnico;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @JoinColumn(name = "idClienteEstructura", referencedColumnName = "idClienteEstructura")
    @ManyToOne(optional = false)
    private ClienteEstructura idClienteEstructura;
    @JoinColumn(name = "idEstatus", referencedColumnName = "idEstatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "idTipoUsuario", referencedColumnName = "idTipoUsuario")
    @ManyToOne(optional = false)
    private TipoUsuario idTipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<UsuarioArea> usuarioAreaList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String numeroTrabajador, String nombre, String apellidoPaterno, String apellidoMaterno, String usuario, String contrasena, String telefonoOficina, String correo, String numeroUnico) {
        this.idUsuario = idUsuario;
        this.numeroTrabajador = numeroTrabajador;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.telefonoOficina = telefonoOficina;
        this.correo = correo;
        this.numeroUnico = numeroUnico;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroTrabajador() {
        return numeroTrabajador;
    }

    public void setNumeroTrabajador(String numeroTrabajador) {
        this.numeroTrabajador = numeroTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumeroOficina() {
        return numeroOficina;
    }

    public void setNumeroOficina(String numeroOficina) {
        this.numeroOficina = numeroOficina;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getTelefonoCasa() {
        return telefonoCasa;
    }

    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMovil1() {
        return movil1;
    }

    public void setMovil1(String movil1) {
        this.movil1 = movil1;
    }

    public String getMovil2() {
        return movil2;
    }

    public void setMovil2(String movil2) {
        this.movil2 = movil2;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {    
        this.numeroUnico = numeroUnico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ClienteEstructura getIdClienteEstructura() {
        return idClienteEstructura;
    }

    public void setIdClienteEstructura(ClienteEstructura idClienteEstructura) {
        this.idClienteEstructura = idClienteEstructura;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public TipoUsuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    @XmlTransient
    public List<UsuarioArea> getUsuarioAreaList() {
        return usuarioAreaList;
    }

    public void setUsuarioAreaList(List<UsuarioArea> usuarioAreaList) {
        this.usuarioAreaList = usuarioAreaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.odessa.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
