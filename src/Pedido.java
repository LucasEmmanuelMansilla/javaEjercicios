import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pedido {
	
	private Long idPedido;
	private Date fecha;
	private Cliente cliente;
	private Negocio negocio;
	private List<PedidoDetalle> listaPedidoDetalle = new ArrayList<PedidoDetalle>();
	
	

	public Pedido() {

	}

	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
		
	
	public Negocio getNegocio() {
		return negocio;
	}
	
	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}
	
	public Long getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}


	public List<PedidoDetalle> getListaPedidoDetalle() {
		return listaPedidoDetalle;
	}


	public void setListaPedidoDetalle(List<PedidoDetalle> listaPedidoDetalle) {
		this.listaPedidoDetalle = listaPedidoDetalle;
	}
	
	
	public Pedido(Date fecha, String producto, Cliente cliente, Negocio negocio, PedidoDetalle listaPedidoDetalle) {
		this.fecha = fecha;
		this.cliente = cliente;
		this.negocio = negocio;
	
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pedido) {
			Pedido other = (Pedido) obj;
			if(this.idPedido.equals(other.idPedido))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
}
