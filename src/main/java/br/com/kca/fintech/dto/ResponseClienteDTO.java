package br.com.kca.fintech.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseClienteDTO {
	
	
	private Long id;
	
	@NotBlank
	@Size(max = 120)
	private String nome;
	
	@NotBlank
	@Size(max = 40)
	private String email;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	private String data_nascimento;

}
