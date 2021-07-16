package com.demo.inventario.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="mercancias", uniqueConstraints={@UniqueConstraint(columnNames={"nombreproducto"})})

public class Mercancia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nombreproducto", nullable=false)
	private String nombreproducto;
	@Column(name="cantidad", nullable=false)
	private int cantidad;
	@Column(name="fechaingreso", nullable=false)
	private Date fechaingreso;
	@ManyToOne
	@JoinColumn(name="idusuarioingreso", nullable=false)
	private Usuario usuarioingreso;
	@Column(name="fechaactualizacion", nullable=true)
	private Date fechaactualizacion;
	@ManyToOne
	@JoinColumn(name="idusuarioactual", nullable=true)
	private Usuario usuarioactual;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public Usuario getUsuarioingreso() {
		return usuarioingreso;
	}
	public void setUsuarioingreso(Usuario usuarioingreso) {
		this.usuarioingreso = usuarioingreso;
	}
	public Date getFechaactualizacion() {
		return fechaactualizacion;
	}
	public void setFechaactualizacion(Date fechaactualizacion) {
		this.fechaactualizacion = fechaactualizacion;
	}
	public Usuario getUsuarioactual() {
		return usuarioactual;
	}
	public void setUsuarioactual(Usuario usuarioactual) {
		this.usuarioactual = usuarioactual;
	}
	
}
