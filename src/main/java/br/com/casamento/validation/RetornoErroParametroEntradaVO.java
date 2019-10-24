package br.com.casamento.validation;

public class RetornoErroParametroEntradaVO {
	
	private String campo;
	private String mensagem;
	
	public RetornoErroParametroEntradaVO(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	

}
