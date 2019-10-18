package br.com.casamento.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Mesa {

	@Id
	private Long id;
	@NotNull
	private Integer quantidadeCadeiras;
	@NotNull
	private Integer quantidadeCadeirasDisponiveis;

	public Mesa() {

	}

	public Mesa(Long id, Integer cadeiras, Integer quantidadeCaderias) {
		
		this.id = id;
		this.quantidadeCadeiras = quantidadeCaderias;
		this.quantidadeCadeirasDisponiveis = quantidadeCaderias;
	}

	public Mesa(Long id, Integer quantidadeCadeiras) {

		this.id = id;
		this.quantidadeCadeiras = quantidadeCadeiras;
		this.quantidadeCadeirasDisponiveis = quantidadeCadeiras;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidadeCadeiras() {
		return quantidadeCadeiras;
	}

	public Integer getQuantidadeCadeirasDisponiveis() {
		return quantidadeCadeirasDisponiveis;
	}

}
