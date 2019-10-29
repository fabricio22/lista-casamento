package br.com.casamento.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToOne
	private Grupo grupo;
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Mesa mesa;

	public Pessoa() {

	}

	public Pessoa(String nome, Grupo grupo, Mesa mesa) {
		this.nome = nome;
		this.grupo = grupo;
		this.mesa = mesa;
	}
	
	public Pessoa(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Grupo getGrupo() {
        return grupo;
	}
	
	public Mesa getMesa() {
		return mesa;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
