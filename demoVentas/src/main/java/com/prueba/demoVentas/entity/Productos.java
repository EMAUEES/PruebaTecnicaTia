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
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByCodigoBarras", query = "SELECT p FROM Productos p WHERE p.codigoBarras = :codigoBarras"),
    @NamedQuery(name = "Productos.findByPvp", query = "SELECT p FROM Productos p WHERE p.pvp = :pvp"),
    @NamedQuery(name = "Productos.findByFechaCreacion", query = "SELECT p FROM Productos p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Productos.findByFechaActualizacion", query = "SELECT p FROM Productos p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Productos.findByEstado", query = "SELECT p FROM Productos p WHERE p.estado = :estado")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "codigoBarras")
    private long codigoBarras;
    @Basic(optional = false)
    @Column(name = "pvp")
    private double pvp;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<LocalProducto> localProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<TransaccionProducto> transaccionProductoCollection;

    public Productos() {
    	this.fechaCreacion = new Date();
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(Integer idProducto, String nombre, long codigoBarras, double pvp, int estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigoBarras = codigoBarras;
        this.pvp = pvp;
        this.estado = estado;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
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

    public Collection<LocalProducto> getLocalProductoCollection() {
        return localProductoCollection;
    }

    public void setLocalProductoCollection(Collection<LocalProducto> localProductoCollection) {
        this.localProductoCollection = localProductoCollection;
    }

    public Collection<TransaccionProducto> getTransaccionProductoCollection() {
        return transaccionProductoCollection;
    }

    public void setTransaccionProductoCollection(Collection<TransaccionProducto> transaccionProductoCollection) {
        this.transaccionProductoCollection = transaccionProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication4.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
