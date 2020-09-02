package com.sinensia.micro2azul.jobmicro2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sinensia.micro2azul.config.AbstractJobConfig;
import com.sinensia.micro2azul.jobmicro2.dto.DetallePedidoDto;

@Configuration
public class JobPedidoConfig extends AbstractJobConfig {

	private static final String SELECT;
	
	static {
		SELECT = new StringBuilder()
				.append(" SELECT")
				.append("    pedidos.codigo_pedido,")
				.append("    productos.CODIGO_PRODUCTO,")
				.append("    productos.CODIGO_PROVEEDOR,")
				.append("    productos.nombre,")
				.append("    productos.tipoProducto,")
				.append("    detalle_pedidos.cantidad,")
				.append("    productos.peso *  detalle_pedidos.cantidad as peso_total")
				.append(" FROM")
				.append("    detalle_pedidos")
				.append("        JOIN")
				.append("    pedidos ON detalle_pedidos.CODIGO_PEDIDO = pedidos.CODIGO_PEDIDO")
				.append("        JOIN")
				.append("    productos ON detalle_pedidos.CODIGO_PRODUCTO = productos.CODIGO_PRODUCTO")
				.append("        AND detalle_pedidos.CODIGO_PROVEEDOR = productos.CODIGO_PROVEEDOR")
				.append(" WHERE")
				.append("    pedidos.fecha > ? and pedidos.fecha < ? ").toString();
	}
	

	@Bean
	@Qualifier("jobPedido")
	public Job jobPedido() {
		return jobBuilderFactory.get("jobPedido")
				.flow(step())
				.end()
				.build();
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step")
				.<DetallePedidoDto, DetallePedidoDto>chunk(5)
				.reader(reader(null, null))
				.writer(writer(null)).build();
	}

	@Bean(destroyMethod = "") // Acto de FE
	@StepScope // se instancia un nuevo reader para cada vez que se ejecuta el step...
	public JdbcCursorItemReader<DetallePedidoDto> reader(@Value("#{jobParameters['fecha1']}") Date fecha1,
			@Value("#{jobParameters['fecha2']}") Date fecha2) {

		JdbcCursorItemReader<DetallePedidoDto> cursorItemReader = new JdbcCursorItemReader<>();

		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql(SELECT);

		cursorItemReader.setPreparedStatementSetter(new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setDate(1, new java.sql.Date(fecha1.getTime()));
				ps.setDate(2, new java.sql.Date(fecha2.getTime()));
			}
		});
		System.out.println(new java.sql.Date(fecha1.getTime()));
		System.out.println(new java.sql.Date(fecha2.getTime()));
		cursorItemReader.setRowMapper(new JobPedidoRowMapper());
		return cursorItemReader;
	}
	@Bean(destroyMethod = "") // Acto de FE
	@StepScope
	public FlatFileItemWriter<DetallePedidoDto> writer(@Value("#{jobParameters['fecha1']}") Date fecha1) {

		FlatFileItemWriter<DetallePedidoDto> writer = new FlatFileItemWriter<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
		writer.setResource(
				new FileSystemResource(
						new StringBuilder().append("materiales/salidas/pedidos_")
						.append(sdf.format(fecha1)).toString()));

		
		BeanWrapperFieldExtractor<DetallePedidoDto> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] {
				"codigoPedido" 
				,"codigoProducto"
				,"codigoProveedor"
				,"nombre"
				,"tipoProducto"
				,"cantidad"
				,"pesoTotal"          
				});
		
		DelimitedLineAggregator<DetallePedidoDto> lineAggregator = new DelimitedLineAggregator<>();
		
		lineAggregator.setDelimiter(",");
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
 		
		return writer;
	}


}
