package com.sinensia.micro2azul.jobmicro2.dto;

import java.io.Serializable;

public class DetallePedidoDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigoPedido;
	private Long codigoProducto;
	private Long codigoProveedor;
	private String nombre;
	private String tipoProducto;
	private Long cantidad;
	private Double pesoTotal;
	public DetallePedidoDto() {
	}
	public Long getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(Long codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public Long getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public Long getCodigoProveedor() {
		return codigoProveedor;
	}
	public void setCodigoProveedor(Long codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPesoTotal() {
		return pesoTotal;
	}
	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	@Override
	public String toString() {
		return "DetallePedidoDto [codigoPedido=" + codigoPedido + ", codigoProducto=" + codigoProducto
				+ ", codigoProveedor=" + codigoProveedor + ", nombre=" + nombre + ", tipoProducto=" + tipoProducto
				+ ", cantidad=" + cantidad + ", pesoTotal=" + pesoTotal + "]";
	}
	

}
