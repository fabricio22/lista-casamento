package br.com.casamento.vo.saida;

import java.util.List;

import br.com.casamento.modelo.Pessoa;

public class PessoasVO {

	private Long total;
	private Integer totalPages;
	private List<Pessoa> pessoas;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
