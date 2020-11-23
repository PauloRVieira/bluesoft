package br.com.bluesoft.desafio.dto;

import lombok.Data;

@Data
public class ProdutoPorPedidoDTO {
	private String gtin;
	private Integer quantidade;
	public ProdutoPorPedidoDTO() {
	}
	public ProdutoPorPedidoDTO(String string, int i) {
		setGtin(string);
		setQuantidade(i);
	}
	public String getGtin() {
		return gtin;
	}
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
