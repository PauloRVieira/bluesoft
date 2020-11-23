package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.dto.FornecedorProdutoDTO;
import br.com.bluesoft.desafio.dto.FornecedorPorPrecoDTO;
import br.com.bluesoft.desafio.dto.PrecoPorProdutoDTO;
import br.com.bluesoft.desafio.dto.ProdutoPorPedidoDTO;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItem;
import br.com.bluesoft.desafio.model.PedidoItemId;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.rest.ProdutoRestTemplate;

@Service
public class PedidoService {

	@Autowired
	private ProdutoRestTemplate produtoWS;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Transactional
	public List<Pedido> novo(List<ProdutoPorPedidoDTO> produtosPedido) {
		List<ProdutoPorPedidoDTO> produtosInformados = obterProdutosInformados(produtosPedido);

		return agruparPedidosPorFornecedor(produtosInformados).values().stream()
				.map(pedido -> pedidoRepository.save(pedido))
				.collect(Collectors.toList());
	}

	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}

	public List<ProdutoPorPedidoDTO> obterProdutosInformados(List<ProdutoPorPedidoDTO> produtosPedido) {
		return produtosPedido.stream().filter(produtoPedido -> produtoPedido.getQuantidade() > 0)
				.collect(Collectors.toList());
	}

	public Map<Fornecedor, Pedido> agruparPedidosPorFornecedor(List<ProdutoPorPedidoDTO> produtosPedido) {
		Map<Fornecedor, Pedido> agrupadorPedidoFornecedor = new HashMap<>();

		for (ProdutoPorPedidoDTO produtoPedido : produtosPedido) {
			List<FornecedorProdutoDTO> fornecedores = produtoWS.consultarFornecedores(produtoPedido.getGtin());
			FornecedorPorPrecoDTO melhorFornecedorDTO = buscarFornecedor(produtoPedido.getQuantidade(),
					fornecedores);
			Produto produto = produtoService.buscarPorGtin(produtoPedido.getGtin());

			if (melhorFornecedorDTO.getFornecedor().isPresent()) {
				Fornecedor fornecedor = buscarFornecedor(melhorFornecedorDTO);
				Optional<Pedido> opPedido = Optional.ofNullable(agrupadorPedidoFornecedor.get(fornecedor));

				if (!opPedido.isPresent()) {
					opPedido = Optional.of(new Pedido(fornecedor));
					agrupadorPedidoFornecedor.put(fornecedor, opPedido.get());
				}

				PedidoItemId pedidoItenId = new PedidoItemId();
				pedidoItenId.setProduto(produto);
				pedidoItenId.setPedido(opPedido.get());

				PedidoItem pedidoIten = new PedidoItem();
				pedidoIten.setId(pedidoItenId);
				pedidoIten.setPreco(melhorFornecedorDTO.getMenorPreco());
				pedidoIten.setQuantidade(produtoPedido.getQuantidade());
				pedidoIten.setTotal(pedidoIten.getPreco().multiply(BigDecimal.valueOf(pedidoIten.getQuantidade())));

				opPedido.get().getItens().add(pedidoIten);
			}
		}

		return agrupadorPedidoFornecedor;
	}

	private Fornecedor buscarFornecedor(FornecedorPorPrecoDTO melhorFornecedorDTO) {
		Fornecedor melhorFornecedor = melhorFornecedorDTO.getFornecedor().get();
		return Optional.ofNullable(fornecedorRepository.findOne(melhorFornecedor.getCnpj())).orElse(melhorFornecedor);
	}

	public FornecedorPorPrecoDTO buscarFornecedor(final Integer quantidade,
			List<FornecedorProdutoDTO> fornecedores) {
		Fornecedor fornecedor = null;
		BigDecimal menorPreco = null;

		for (FornecedorProdutoDTO fornecedorProduto : fornecedores) {
			for (PrecoPorProdutoDTO precoProduto : fornecedorProduto.getPrecos()) {
				if ((quantidade >= precoProduto.getQuantidadeMinima())
						&& ((menorPreco == null) || (precoProduto.getPreco().compareTo(menorPreco) < 0))) {
					fornecedor = new Fornecedor(fornecedorProduto.getCnpj(), fornecedorProduto.getNome());
					menorPreco = precoProduto.getPreco();
				}
			}
		}

		return new FornecedorPorPrecoDTO(Optional.ofNullable(fornecedor), menorPreco);
	}

}
