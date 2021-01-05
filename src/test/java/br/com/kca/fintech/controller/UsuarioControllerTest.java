package br.com.kca.fintech.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.kca.fintech.dto.ClienteDTO;
import br.com.kca.fintech.dto.ResponseClienteDTO;
import br.com.kca.fintech.entity.Cliente;
import br.com.kca.fintech.service.ClienteServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {
	
	public static final String BOOK_API_URL_PATH = "/api/v1/clientes/";
    private MockMvc mockMvc;

    @Mock
    private ClienteServiceImpl clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTisCalledThenAClienteShouldBeCreated() throws Exception {
        ClienteDTO clienteDTO = createClienteDTO();
        ResponseClienteDTO expectedMessageResponse = ResponseClienteDTO.builder()
                .build();

        Mockito.when(clienteService.createCliente(clienteDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(post(BOOK_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clienteDTO)))
                .andExpect(status().isCreated());
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

