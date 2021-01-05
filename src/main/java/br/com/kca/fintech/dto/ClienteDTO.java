package br.com.kca.fintech.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.not.blank}")
	@Size(max = 120)
	private String nome;
	
	@NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
	@Size(max = 40)
	private String email;
	
	@NotBlank
	@Size(max = 14)
	private String cpf;
	
	@NotBlank
	private String data_nascimento;
	
	

}
