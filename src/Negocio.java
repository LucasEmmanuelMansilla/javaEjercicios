import java.util.ArrayList;
import java.util.List;

public class Negocio {

	private String razonSocial;
	private Long idNegocio;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	
	public Negocio(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public Negocio() {
		
	}
	
	public List<Cliente> getListaCliente() {
		return listaClientes;
	}
	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaClientes = listaCliente;
	}
	public List<Pedido> getListaPedido() {
		return listaPedidos;
	}
	public void setListaPedido(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String negocio) {
		this.razonSocial = negocio;
	}
	public Long getIdNegocio() {
		return idNegocio;
	}
	public void setIdNegocio(Long idNegocio) {
		this.idNegocio = idNegocio;
	}

	
	public boolean equals(Object obj) {
		if(obj instanceof Negocio) {
			Negocio other = (Negocio) obj;
			if(this.idNegocio.equals(other.idNegocio))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}


}
