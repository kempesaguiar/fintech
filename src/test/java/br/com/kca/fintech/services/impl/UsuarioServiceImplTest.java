package br.com.kca.fintech.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;
import br.com.kca.fintech.exception.ClienteNotFoundException;
import br.com.kca.fintech.repository.ClienteRepository;
import br.com.kca.fintech.service.ClienteServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Test
	void whenGivenExistingIdThenReturnThisCliente() throws ClienteNotFoundException {
		Cliente expectedFoundCliente = createCliente();
		
		when(clienteRepository.findById(expectedFoundCliente.getId())).thenReturn(Optional.of(expectedFoundCliente));
        
        Optional<Cliente> clienteDTO = clienteRepository.findById(expectedFoundCliente.getId());

        assertEquals(expectedFoundCliente.getNome(), clienteDTO.get().getNome());
	}
	
   
    
    public Cliente createCliente() {
    	Cliente cliente = new Cliente();
    	cliente.setId(Long.valueOf("1"));
    	cliente.setNome("Teste");
    	cliente.setEmail("teste@teste.com.br");
    	cliente.setCpf("12345678946");
    	cliente.setData_nascimento("1960-09-09");
    	return cliente;
    }
    
    public ClienteDTO createClienteDTO() {
    	ClienteDTO cliente = new ClienteDTO();
    	cliente.setId(Long.valueOf("1"));
    	cliente.setNome("Teste");
    	cliente.setEmail("teste@teste.com.br");
    	cliente.setCpf("12345678946");
    	cliente.setData_nascimento("1960-09-09");
    	return cliente;
    }
}
