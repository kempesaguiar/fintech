package br.com.kca.fintech.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;
import br.com.kca.fintech.exception.ClienteExistCpfException;
import br.com.kca.fintech.exception.ClienteNotFoundException;

public interface ClienteService {
	
	List<Cliente> listar();
	
	ResponseClienteDTO listaClienteUnico(Long id) throws ClienteNotFoundException;
	
	ResponseClienteDTO createCliente(@Valid ClienteDTO clienteDTO) throws ClienteExistCpfException;
	
	ResponseClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) 
			throws ClienteNotFoundException;
	
	void excluirCliente(Long id) throws ClienteNotFoundException;
	
	

}
