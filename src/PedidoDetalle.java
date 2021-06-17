import java.math.BigDecimal;


public class PedidoDetalle {
	private Long idPedidoDetalle;
	private String descProducto;
	private BigDecimal monto;

	public Long getIdDetallePedido() {
		return idPedidoDetalle;
	}

	public void setIdDetallePedido(Long idDetallePedido) {
		this.idPedidoDetalle = idDetallePedido;
	}

	
	public String getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	
	public PedidoDetalle() {

	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PedidoDetalle) {
			PedidoDetalle other = (PedidoDetalle) obj;
			if(this.idPedidoDetalle.equals(other.idPedidoDetalle))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
	}
	
}
