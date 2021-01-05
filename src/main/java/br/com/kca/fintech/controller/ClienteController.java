package br.com.kca.fintech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;
import br.com.kca.fintech.exception.ClienteExistCpfException;
import br.com.kca.fintech.exception.ClienteNotFoundException;
import br.com.kca.fintech.mapper.ClienteMapper;
import br.com.kca.fintech.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> cliente = clienteService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseClienteDTO> listaClienteUnico(@PathVariable Long id) 
			throws ClienteNotFoundException {
		ResponseClienteDTO response = clienteService.listaClienteUnico(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	public ResponseEntity<ResponseClienteDTO> createCliente(@RequestBody @Valid ClienteDTO clientedto) 
	throws ClienteExistCpfException {
		ResponseClienteDTO response = clienteService.createCliente(clientedto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clientedto) 
			throws ClienteNotFoundException {
		ResponseClienteDTO response = clienteService.atualizarCliente(id, clientedto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirCliente(@PathVariable Long id) throws ClienteNotFoundException {
		clienteService.excluirCliente(id);
		return ResponseEntity.status(HttpStatus.OK).body("Cliente " + id + " excluído com sucesso ");
	}

}
