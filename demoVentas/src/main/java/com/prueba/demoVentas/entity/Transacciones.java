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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "transacciones")
@NamedQueries({
    @NamedQuery(name = "Transacciones.findAll", query = "SELECT t FROM Transacciones t"),
    @NamedQuery(name = "Transacciones.findByIdTransaccion", query = "SELECT t FROM Transacciones t WHERE t.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "Transacciones.findByTipo", query = "SELECT t FROM Transacciones t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Transacciones.findByFechaCreacion", query = "SELECT t FROM Transacciones t WHERE t.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Transacciones.findByFechaActualizacion", query = "SELECT t FROM Transacciones t WHERE t.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Transacciones.findByEstado", query = "SELECT t FROM Transacciones t WHERE t.estado = :estado")})
public class Transacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTransaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
    private Collection<TransaccionProducto> transaccionProductoCollection;

    public Transacciones() {
    	this.fechaCreacion = new Date();
    }

    public Transacciones(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transacciones(Integer idTransaccion, String tipo, int estado) {
        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Collection<TransaccionProducto> getTransaccionProductoCollection() {
        return transaccionProductoCollection;
    }

    public void setTransaccionProductoCollection(Collection<TransaccionProducto> transaccionProductoCollection) {
        this.transaccionProductoCollection = transaccionProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transacciones)) {
            return false;
        }
        Transacciones other = (Transacciones) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.demoVentas.entity.Transacciones[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
