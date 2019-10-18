package br.com.casamento.vo;

import br.com.casamento.modelo.Mesa;

public class MesaVo {

	private Long id;
	private Integer cadeirasDisponiveis;
	private Integer quantidadeCadeiras;

	public Long getId() {
		return id;
	}

	public Integer getCadeirasDisponiveis() {
		return cadeirasDisponiveis;
	}
	
	public Integer getQuantidadeCadeiras() {
		return quantidadeCadeiras;
	}

	public MesaVo() {

	}
	
	public MesaVo(Long idMesa, Integer quantidadeCadeiras) {
		this.id = idMesa;
		this.quantidadeCadeiras = quantidadeCadeiras;
		this.cadeirasDisponiveis = quantidadeCadeiras;
	}
	
	public Mesa converter(MesaVo dadosEntradaVo) {
		
		Mesa mesa = new Mesa(dadosEntradaVo.getId(), dadosEntradaVo.getQuantidadeCadeiras());
		return mesa;
	}

}
