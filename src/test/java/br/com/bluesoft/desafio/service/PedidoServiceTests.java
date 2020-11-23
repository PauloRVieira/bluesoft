package br.com.bluesoft.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bluesoft.desafio.dto.FornecedorProdutoDTO;
import br.com.bluesoft.desafio.dto.PrecoPorProdutoDTO;
import br.com.bluesoft.desafio.dto.ProdutoPorPedidoDTO;
import br.com.bluesoft.desafio.repository.FornecedorRepository;
import br.com.bluesoft.desafio.rest.ProdutoRestTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PedidoServiceTests {

	@InjectMocks
	private PedidoService pedidoService;

	@Mock
	private ProdutoRestTemplate produtoWS;

	@Mock
	private ProdutoService produtoService;

	@Mock
	private FornecedorRepository fornecedorRepository;

	@Before
	public void prepararMocks() {

		FornecedorProdutoDTO fornecedor1 = new FornecedorProdutoDTO();
		fornecedor1.setCnpj("30.782.661/0001-87");
		fornecedor1.setNome("Fornecedor");
		fornecedor1.setPrecos(Arrays.asList(
				new PrecoPorProdutoDTO[] { new PrecoPorProdutoDTO(BigDecimal.valueOf(6.89), 1), new PrecoPorProdutoDTO(BigDecimal.valueOf(5.89), 10), }));

		List<FornecedorProdutoDTO> fornecedores = new ArrayList<FornecedorProdutoDTO>();
		fornecedores.add(fornecedor1);

		when(produtoWS.consultarFornecedores(eq("7894900011517"))).thenReturn(fornecedores);
	}

	@Test
	public void recuperaProdutosTest() {

		List<ProdutoPorPedidoDTO> produtosPedido = Arrays.asList(new ProdutoPorPedidoDTO[] { new ProdutoPorPedidoDTO("7894900011517", 3),
				new ProdutoPorPedidoDTO("7891910000197", 1), new ProdutoPorPedidoDTO("7891000100103", 0), new ProdutoPorPedidoDTO("7891910007110", 0) });

		List<ProdutoPorPedidoDTO> produtosInformados = pedidoService.obterProdutosInformados(produtosPedido);

		assertEquals(produtosInformados.size(), 2);
	}
	
}
