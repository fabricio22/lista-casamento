package br.com.casamento.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToOne
	private Grupo grupo;

	public Pessoa() {

	}

	public Pessoa(String nome, String grupo) {
		this.nome = nome;
		new Grupo(grupo);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
