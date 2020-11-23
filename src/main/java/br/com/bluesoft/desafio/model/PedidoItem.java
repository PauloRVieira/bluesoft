package br.com.bluesoft.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data()
@Entity
@Table(name = "Pedido_Item")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PedidoItemId id;
	private Integer quantidade;
	private BigDecimal preco;
	private BigDecimal total;
	
	public PedidoItemId getId() {
		return id;
	}
	public void setId(PedidoItemId id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
