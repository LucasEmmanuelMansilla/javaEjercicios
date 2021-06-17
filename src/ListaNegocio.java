
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaNegocio {
	
	 public List<Negocio> listaNegocios () {
		 
		 List<Negocio> negocios = new ArrayList<Negocio>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;
		try {
			
			// Lista de todos los pedidos de un negocio en particular ordenado por fecha del pedido (Negocio, PedidoNúmero, PedidoFecha, ClienteApellido, ClienteNombre)
			// Lista de negocios con una lista de pedidos cargados
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicios", "root", "12345");
					
			PreparedStatement traerNegocio = conn.prepareStatement("SELECT ID_NEGOCIO, NOMBRE_PRODUCTO, ID_PEDIDO, RAZON_SOCIAL, FECHA\r\n"
		  		+ "FROM PEDIDO AS pd \r\n"
		  		+ "INNER JOIN NEGOCIO AS ng ON pd.NEGOCIO= ng.ID_NEGOCIO\r\n"
		  		+ "order by FECHA;"
				);
						
			ResultSet negocio = traerNegocio.executeQuery();	
					
			while(negocio.next()) {			
				Long idPedido = negocio.getLong("ID_PEDIDO");			
				String nombreEmpresa = negocio.getString("RAZON_SOCIAL");
				Date fecha = negocio.getTimestamp("FECHA");
				Long idNegocio = negocio.getLong("ID_NEGOCIO");
														
				Negocio nuevoNegocio = new Negocio();
				nuevoNegocio.setIdNegocio(idNegocio);
				nuevoNegocio.setRazonSocial(nombreEmpresa);
				Pedido nuevoPedido = new Pedido();
				nuevoPedido.setIdPedido(idPedido);
				nuevoPedido.setFecha(fecha);
						
				if(!negocios.contains(nuevoNegocio)) {
					nuevoNegocio.getListaPedido().add(nuevoPedido);
					negocios.add(nuevoNegocio);			
				}else {
					int i = negocios.indexOf(nuevoNegocio);
					negocios.get(i).getListaPedido().add(nuevoPedido);
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
		return negocios;
		
	}
	

	public static void main(String[] args) {
		
		ListaNegocio negocios = new ListaNegocio();
		
		for (Negocio ng : negocios.listaNegocios()) {
			System.out.println("Nombre del negocio " + ng.getRazonSocial());
			for(Pedido ped : ng.getListaPedido()) {								
				System.out.println("ID Pedido "+ ped.getIdPedido() + " Fecha del pedido " + ped.getFecha());					
			}		
		}
		
	}
}