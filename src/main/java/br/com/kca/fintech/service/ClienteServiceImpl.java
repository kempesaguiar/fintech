package br.com.kca.fintech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;
import br.com.kca.fintech.exception.ClienteExistCpfException;
import br.com.kca.fintech.exception.ClienteNotFoundException;
import br.com.kca.fintech.mapper.ClienteMapper;
import br.com.kca.fintech.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository clienteRepository;
	
	private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@Override
	public ResponseClienteDTO listaClienteUnico(Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException(id));
		return clienteMapper.toDTOR(cliente);
	}
	
	@Override
	public ResponseClienteDTO createCliente(ClienteDTO clienteDTO) throws ClienteExistCpfException {
		Cliente novoCliente = clienteMapper.toModel(clienteDTO);
		String cpf = clienteDTO.getCpf();
		Cliente existeCpf = clienteRepository.findByCpf(cpf);
		if (existeCpf != null) {
			System.out.println("CPF duplicado");
			throw new ClienteExistCpfException(existeCpf.getId());
		}
		
		Cliente response = clienteRepository.save(novoCliente);
		return clienteMapper.toDTOR(response);
	}
	
	@Override
	public ResponseClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) throws ClienteNotFoundException {
		Cliente atualizaCliente = clienteMapper.toModel(clienteDTO);
		Cliente BuscaCliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException(id));
		atualizaCliente.setId(id);
		Cliente response = clienteRepository.save(atualizaCliente);
		return clienteMapper.toDTOR(atualizaCliente);
		
	}
	
	@Override
	public void excluirCliente(Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException(id));
		clienteRepository.deleteById(id);
	}


}
