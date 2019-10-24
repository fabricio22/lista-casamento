package br.com.casamento.vo.saida;

import br.com.casamento.modelo.Mesa;

public class MesaSaidaVO {
	
	private Long id;
	private Integer quantidadeLugaresDisponiveis;
	private Integer quantidadeCadeiras;
	
	public MesaSaidaVO() {
		
	}
	
	public MesaSaidaVO(Mesa mesa) {
		this.id = mesa.getId();
		this.quantidadeLugaresDisponiveis = mesa.getQuantidadeCadeirasDisponiveis();
		this.quantidadeCadeiras = mesa.getQuantidadeCadeiras();
	}
	
	public Long getId() {
		return id;
	}
	
	public Integer getQuantidadeLugaresDisponiveis() {
		return quantidadeLugaresDisponiveis;
	}
	
	public Integer getQuantidadeCadeiras() {
		return quantidadeCadeiras;
	}
	
}
