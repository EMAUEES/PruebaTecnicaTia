/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.demoVentas.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User A1
 */
@Entity
@Table(name = "localProducto")
@NamedQueries({
    @NamedQuery(name = "LocalProducto.findAll", query = "SELECT l FROM LocalProducto l"),
    @NamedQuery(name = "LocalProducto.findByIdLocalProducto", query = "SELECT l FROM LocalProducto l WHERE l.idLocalProducto = :idLocalProducto"),
    @NamedQuery(name = "LocalProducto.findByStock", query = "SELECT l FROM LocalProducto l WHERE l.stock = :stock"),
    @NamedQuery(name = "LocalProducto.findByFechaCreacion", query = "SELECT l FROM LocalProducto l WHERE l.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "LocalProducto.findByFechaActualizacion", query = "SELECT l FROM LocalProducto l WHERE l.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "LocalProducto.findByEstado", query = "SELECT l FROM LocalProducto l WHERE l.estado = :estado")})
public class LocalProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idLocalProducto")
    private Integer idLocalProducto;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idLocal", referencedColumnName = "idLocal")
    @ManyToOne(optional = false)
    private Local idLocal;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public LocalProducto() {
    	this.fechaCreacion = new Date();
    }

    public LocalProducto(Integer idLocalProducto) {
        this.idLocalProducto = idLocalProducto;
    }

    public LocalProducto(Integer idLocalProducto, int estado) {
        this.idLocalProducto = idLocalProducto;
        this.estado = estado;
    }

    public Integer getIdLocalProducto() {
        return idLocalProducto;
    }

    public void setIdLocalProducto(Integer idLocalProducto) {
        this.idLocalProducto = idLocalProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalProducto != null ? idLocalProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalProducto)) {
            return false;
        }
        LocalProducto other = (LocalProducto) object;
        if ((this.idLocalProducto == null && other.idLocalProducto != null) || (this.idLocalProducto != null && !this.idLocalProducto.equals(other.idLocalProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.demoVentas.entity.LocalProducto[ idLocalProducto=" + idLocalProducto + " ]";
    }
    
}
