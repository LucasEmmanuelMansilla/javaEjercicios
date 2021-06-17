
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class DBConnection {
	
	
	public List<Cliente> listaClientes(){
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicios", "root", "12345");
			//Poner localhost en lugar 10.1.5.70

			
			  PreparedStatement pedirCliente = conn.prepareStatement("SELECT ID_DETALLE_PEDIDO, ID_CLIENTE, RAZON_SOCIAL,ID_PEDIDO, FECHA, sum(MONTO), NOMBRE\r\n"
					+ "FROM PEDIDO AS pd \r\n"
					+ "INNER JOIN DETALLE_PEDIDO AS dp ON dp.PEDIDO=pd.ID_PEDIDO\r\n"
					+ "INNER JOIN NEGOCIO AS ng ON pd.NEGOCIO=ng.ID_NEGOCIO\r\n"
					+ "INNER JOIN CLIENTE AS cl ON pd.CLIENTE=cl.ID_CLIENTE\r\n"
					
					+ "group by ID_PEDIDO;"
					);
			
			ResultSet cliente = pedirCliente.executeQuery();	
					
			while(cliente.next()) {			
				Long idPedido = cliente.getLong("ID_PEDIDO");			
				String nombreEmpresa = cliente.getString("RAZON_SOCIAL");
				Date fecha = cliente.getTimestamp("FECHA");
				BigDecimal total = cliente.getBigDecimal("sum(MONTO)");
				String nombreCliente = cliente.getString("NOMBRE");
				Long idCliente = cliente.getLong("ID_CLIENTE");
				Long idPedidoDetalle = cliente.getLong("ID_DETALLE_PEDIDO");
							
				PedidoDetalle pedidoDetalle = new PedidoDetalle();
				
				pedidoDetalle.setMonto(total);
				pedidoDetalle.setIdDetallePedido(idPedidoDetalle);
		
				Pedido pedido = new Pedido();
				pedido.setFecha(fecha);
				pedido.setIdPedido(idPedido);
		
 				Cliente cliente1 = new Cliente();
 				cliente1.setIdCliente(idCliente);
 				cliente1.setNombre(nombreCliente);

 				
 				if(!clientes.contains(cliente1)) {								
 					clientes.add(cliente1);
 					}else {
 						if(cliente1.getListaPedidos().contains(pedido)) {
 	 						int ind = cliente1.getListaPedidos().indexOf(pedido);
 	 						cliente1.getListaPedidos().get(ind).getListaPedidoDetalle().add(pedidoDetalle);	
 	 					}else {					
 	 						pedido.getListaPedidoDetalle().add(pedidoDetalle);
 	 						cliente1.getListaPedidos().add(pedido);
 	 					}
 	 					
 						int i = clientes.indexOf(cliente1);
 						clientes.get(i).getListaPedidos().add(pedido);
 					}											 				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return clientes;
		
	}

	public static void main(String[] args) {
		
		DBConnection clientes = new DBConnection();
		
		for (Cliente cl : clientes.listaClientes()) {
			System.out.println("Nombre del negocio " + cl.getNombre());
			for(Pedido ped : cl.getListaPedidos()) {								
				System.out.println("ID Pedido "+ ped.getIdPedido());	
				for(PedidoDetalle pd : ped.getListaPedidoDetalle()) {
					System.out.println("Monto " + pd.getMonto());
				}
			}		
		}	
	}
}

// No darle tanta importancia a los parámetros de la querys

// Agregar parámetros si es necesario