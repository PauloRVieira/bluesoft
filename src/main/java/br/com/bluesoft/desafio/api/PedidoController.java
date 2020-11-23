package br.com.bluesoft.desafio.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.dto.PedidoDTO;
import br.com.bluesoft.desafio.dto.ProdutoPorPedidoDTO;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping()
	public ResponseEntity<Collection<PedidoDTO>> adiciona(@RequestBody List<ProdutoPorPedidoDTO> produtosPedido) {
		return ResponseEntity.status(HttpStatus.CREATED).
				body(toDTO(pedidoService.novo(produtosPedido)));
	}

	@GetMapping()
	public ResponseEntity<?> todos() {
		List<Pedido> pedidos = pedidoService.buscarTodos();
		return !pedidos.isEmpty() ? 
				ResponseEntity.ok(toDTO(pedidos)) : 
					ResponseEntity.noContent().build();
	}

	private List<PedidoDTO> toDTO(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(pedido -> modelMapper.map(pedido, PedidoDTO.class))
				.collect(Collectors.toList());
	}

}
