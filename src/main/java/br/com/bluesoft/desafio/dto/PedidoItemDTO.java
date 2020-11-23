package br.com.bluesoft.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProdutoDTO produto;
	private Integer quantidade;
	private BigDecimal preco;
	private BigDecimal total;
	public PedidoItemDTO() {
	}
	public PedidoItemDTO(ProdutoDTO produtoDTO, Integer quantidade2, BigDecimal preco2, BigDecimal total2) {
		setProduto(produtoDTO);
		setQuantidade(quantidade2);
		setPreco(preco2);
		setTotal(total2);
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
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
