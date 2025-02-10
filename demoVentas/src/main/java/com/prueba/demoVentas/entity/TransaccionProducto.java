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
@Table(name = "transaccionProducto")
@NamedQueries({
    @NamedQuery(name = "TransaccionProducto.findAll", query = "SELECT t FROM TransaccionProducto t"),
    @NamedQuery(name = "TransaccionProducto.findByIdTransProducto", query = "SELECT t FROM TransaccionProducto t WHERE t.idTransProducto = :idTransProducto"),
    @NamedQuery(name = "TransaccionProducto.findByCantidad", query = "SELECT t FROM TransaccionProducto t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TransaccionProducto.findByIva", query = "SELECT t FROM TransaccionProducto t WHERE t.iva = :iva"),
    @NamedQuery(name = "TransaccionProducto.findByFechaCreacion", query = "SELECT t FROM TransaccionProducto t WHERE t.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "TransaccionProducto.findByFechaActualizacion", query = "SELECT t FROM TransaccionProducto t WHERE t.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "TransaccionProducto.findByEstado", query = "SELECT t FROM TransaccionProducto t WHERE t.estado = :estado")})
public class TransaccionProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTransProducto")
    private Integer idTransProducto;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "iva")
    private int iva;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "idTransaccion", referencedColumnName = "idTransaccion")
    @ManyToOne(optional = false)
    private Transacciones idTransaccion;

    public TransaccionProducto() {
    	this.fechaCreacion = new Date();
    }

    public TransaccionProducto(Integer idTransProducto) {
        this.idTransProducto = idTransProducto;
    }

    public TransaccionProducto(Integer idTransProducto, int cantidad, int iva, int estado) {
        this.idTransProducto = idTransProducto;
        this.cantidad = cantidad;
        this.iva = iva;
        this.estado = estado;
    }

    public Integer getIdTransProducto() {
        return idTransProducto;
    }

    public void setIdTransProducto(Integer idTransProducto) {
        this.idTransProducto = idTransProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
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

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Transacciones getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Transacciones idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransProducto != null ? idTransProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionProducto)) {
            return false;
        }
        TransaccionProducto other = (TransaccionProducto) object;
        if ((this.idTransProducto == null && other.idTransProducto != null) || (this.idTransProducto != null && !this.idTransProducto.equals(other.idTransProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.demoVentas.entity.TransaccionProducto[ idTransProducto=" + idTransProducto + " ]";
    }
    
}
