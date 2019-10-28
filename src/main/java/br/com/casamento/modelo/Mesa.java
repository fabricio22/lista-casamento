package br.com.casamento.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mesa {

	@Id
	private Long id;
	private Integer quantidadeCadeiras;
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

	public void addMaisCadeiras(Integer quantidade) {
		this.quantidadeCadeiras = this.quantidadeCadeiras + quantidade;
		this.quantidadeCadeirasDisponiveis = this.getQuantidadeCadeirasDisponiveis() + quantidade;
	}

	public void setQuantidadeCadeirasDisponiveis(Integer quantidade) throws Exception {

		if (quantidade >= 0 && this.getQuantidadeCadeirasDisponiveis() >= 0) {
			this.quantidadeCadeirasDisponiveis = quantidade;
		} else {
			throw new Exception("numero de cadeiras disponiveis excedito!");
		}
	}

	public boolean getTemLugarDisponivel() {

		return quantidadeCadeirasDisponiveis > 0 ? true : false;
	}

	public void aumentarCadeirasDisponiveis() {
		this.quantidadeCadeirasDisponiveis = this.getQuantidadeCadeirasDisponiveis() + 1;
	}

	public void diminuirCadeirasDisponiveis() {
		if (this.getQuantidadeCadeirasDisponiveis() >= 0) {
			this.quantidadeCadeirasDisponiveis = this.getQuantidadeCadeirasDisponiveis() - 1;
		}
	}

}
