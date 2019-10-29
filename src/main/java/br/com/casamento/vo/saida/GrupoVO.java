package br.com.casamento.vo.saida;

import java.util.List;

import br.com.casamento.modelo.Grupo;

public class GrupoVO {
	
	private Long total;
	private Integer totalPages;
	private List<Grupo> grupo;
	
	public GrupoVO() {
	}

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

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}
}
