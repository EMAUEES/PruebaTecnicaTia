/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.demoVentas.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User A1
 */
@Entity
@Table(name = "local")
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findByIdLocal", query = "SELECT l FROM Local l WHERE l.idLocal = :idLocal"),
    @NamedQuery(name = "Local.findByNombre", query = "SELECT l FROM Local l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Local.findByProvincia", query = "SELECT l FROM Local l WHERE l.provincia = :provincia"),
    @NamedQuery(name = "Local.findByFechaCreacion", query = "SELECT l FROM Local l WHERE l.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Local.findByFechaActualizacion", query = "SELECT l FROM Local l WHERE l.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Local.findByEstado", query = "SELECT l FROM Local l WHERE l.estado = :estado")})
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idLocal")
    private Integer idLocal;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocal")
    private Collection<Transacciones> transaccionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocal")
    private Collection<LocalProducto> localProductoCollection;

    public Local() {
    	this.fechaCreacion = new Date();
    }

    public Local(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Local(Integer idLocal, String nombre, int estado) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Collection<Transacciones> getTransaccionesCollection() {
        return transaccionesCollection;
    }

    public void setTransaccionesCollection(Collection<Transacciones> transaccionesCollection) {
        this.transaccionesCollection = transaccionesCollection;
    }

    public Collection<LocalProducto> getLocalProductoCollection() {
        return localProductoCollection;
    }

    public void setLocalProductoCollection(Collection<LocalProducto> localProductoCollection) {
        this.localProductoCollection = localProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocal != null ? idLocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.idLocal == null && other.idLocal != null) || (this.idLocal != null && !this.idLocal.equals(other.idLocal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.demoVentas.entity.Local[ idLocal=" + idLocal + " ]";
    }
    
}
