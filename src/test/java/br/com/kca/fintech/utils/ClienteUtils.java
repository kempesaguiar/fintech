package br.com.kca.fintech.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.entity.Cliente;

public class ClienteUtils {
	
	private static final Faker faker = Faker.instance();
	
	public static ClienteDTO createFakeClienteDTO() {
        return ClienteDTO.builder()
        		.id(faker.number().randomNumber())
                .nome(faker.book().title())
                .email("teste@teste.com.br")
                .cpf("1234567811")
                .data_nascimento("1970-10-05")
                .build();
    }

    public static Cliente createFakeCliente() {
        return Cliente.builder()
        		.id(faker.number().randomNumber())
                .nome(faker.book().title())
                .email("teste@teste.com.br")
                .cpf("1234567811")
                .data_nascimento("1970-10-05")
                .build();
    }

    public static Cliente createFakeClienteFrom(ClienteDTO clienteDTO) {
        return Cliente.builder()
        		.id(faker.number().randomNumber())
                .nome(faker.book().title())
                .email("teste@teste.com.br")
                .cpf("1234567811")
                .data_nascimento("1970-10-05")
                .build();
    }

    public static String asJsonString(ClienteDTO ClienteDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(ClienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
