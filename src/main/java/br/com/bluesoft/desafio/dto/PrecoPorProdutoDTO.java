package br.com.bluesoft.desafio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PrecoPorProdutoDTO {

	private BigDecimal preco;

	@JsonProperty(value = "quantidade_minima")
	private Integer quantidadeMinima;

	public PrecoPorProdutoDTO() {
	}
	public PrecoPorProdutoDTO(BigDecimal valueOf, int i) {
		setPreco(valueOf);
		setQuantidadeMinima(i);
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
}
