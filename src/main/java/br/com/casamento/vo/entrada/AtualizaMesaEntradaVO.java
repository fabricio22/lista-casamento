package br.com.casamento.vo.entrada;

import br.com.casamento.modelo.Mesa;
import br.com.casamento.repository.MesaRepository;

public class AtualizaMesaEntradaVO {

	private Integer quantidadeCadeiras;

	public AtualizaMesaEntradaVO() {

	}

	public Integer getQuantidadeCadeiras() {
		return quantidadeCadeiras;
	}

	public Mesa atualizar(Long id, MesaRepository mesaRepository) {
		Mesa mesa = mesaRepository.getOne(id);
		mesa.addMaisCadeiras(this.quantidadeCadeiras);
		return mesa;
	}

}
