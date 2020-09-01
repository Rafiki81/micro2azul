package com.sinensia.micro2azul.jobmicro2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sinensia.micro2azul.jobmicro2.dto.DetallePedidoDto;

public class JobPedidoRowMapper implements RowMapper<DetallePedidoDto> {

	@Override
	public DetallePedidoDto mapRow(ResultSet rs, int rowNum) throws SQLException {

		DetallePedidoDto detallePedidoDto = new DetallePedidoDto();
		
		detallePedidoDto.setCodigoPedido(rs.getLong("CODIGO_PEDIDO"));
		detallePedidoDto.setCodigoProducto(rs.getLong("CODIGO_PRODUCTO"));
		detallePedidoDto.setCodigoProveedor(rs.getLong("CODIGO_PROVEEDOR"));
		detallePedidoDto.setNombre(rs.getString("nombre"));
		detallePedidoDto.setTipoProducto(rs.getString("tipoProducto"));
		detallePedidoDto.setCantidad(rs.getLong("cantidad"));
		detallePedidoDto.setPesoTotal(rs.getDouble("peso_total"));

		return detallePedidoDto;
	}
}
