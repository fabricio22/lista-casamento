package br.com.casamento.vo.entrada;

import javax.validation.constraints.NotNull;

import br.com.casamento.modelo.Mesa;

public class MesaEntradaVO {

	private Long id;
	private Integer cadeirasDisponiveis;
	@NotNull
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

	public MesaEntradaVO() {

	}
	
	public MesaEntradaVO(Long idMesa, Integer quantidadeCadeiras) {
		this.id = idMesa;
		this.quantidadeCadeiras = quantidadeCadeiras;
		this.cadeirasDisponiveis = quantidadeCadeiras;
	}
	
	public Mesa converter(MesaEntradaVO dadosEntradaVo) {
		
		Mesa mesa = new Mesa(dadosEntradaVo.getId(), dadosEntradaVo.getQuantidadeCadeiras());
		return mesa;
	}

}
