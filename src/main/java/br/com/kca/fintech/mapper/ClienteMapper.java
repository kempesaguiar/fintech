package br.com.kca.fintech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
	
	Cliente toModel(ClienteDTO clienteDTO);
	
	ClienteDTO toDTO(Cliente cliente);
	
	Cliente toModelR(ResponseClienteDTO clienteDTO);
	
	ResponseClienteDTO toDTOR(Cliente cliente);
	
	

}
