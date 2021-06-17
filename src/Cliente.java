
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private Long idCliente;
	private String nombre;
	private Long dni;
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	private Negocio negocio;
	
	public Cliente() {
	
		}
		
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}


	public void setListaPedidos(List<Pedido> pedidos) {
		this.listaPedidos = pedidos;
	}


	public Negocio getNegocio() {
		return negocio;
	}


	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}


	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cliente) {
			Cliente other = (Cliente) obj;
			if(this.idCliente.equals(other.idCliente))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	


	
}
