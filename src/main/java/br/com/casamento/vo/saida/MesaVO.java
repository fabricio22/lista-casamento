package br.com.casamento.vo.saida;

import java.util.List;

import br.com.casamento.modelo.Mesa;

public class MesaVO {
	private Long total;
	private Integer totalPages;
	private List<Mesa> mesa;
	
	public MesaVO() {
		
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

	public List<Mesa> getMesa() {
		return mesa;
	}

	public void setMesa(List<Mesa> mesa) {
		this.mesa = mesa;
	}
	
}
