package br.com.bluesoft.desafio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data()
public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private FornecedorDTO fornecedor;
	private List<PedidoItemDTO> itens = new ArrayList<>();

	public PedidoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FornecedorDTO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorDTO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<PedidoItemDTO> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItemDTO> itens) {
		this.itens = itens;
	}

}
