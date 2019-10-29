package br.com.casamento.vo.entrada;

import java.util.Optional;

import br.com.casamento.modelo.Mesa;
import br.com.casamento.modelo.Pessoa;
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.repository.PessoaRepository;

public class AtualizarPessoaEntradaVO {

	private String nome;
	private Long idGrupo;
	private Long idMesa;

	public AtualizarPessoaEntradaVO() {

	}

	public AtualizarPessoaEntradaVO(String nome, Long grupo, Long mesa) {
		this.nome = nome;
		this.idGrupo = grupo;
		this.idMesa = mesa;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getIdGrupo() {
		return this.idGrupo;
	}

	public Long getIdMesa() {
		return this.idMesa;
	}

	
	public Optional<Pessoa> atualizar(Long id, PessoaRepository pessoaRepository, MesaRepository mesaRepository,
			GrupoRepository grupoRepository) throws Exception {

		Optional<Pessoa> pessoa = null;
		boolean temLugarDisponivel = mesaRepository.getOne(this.idMesa).getTemLugarDisponivel();

		if (temLugarDisponivel) {

			pessoa = pessoaRepository.findById(id);

			if (!pessoa.isPresent()) {
				return pessoa;
			} else {
				Mesa mesaAntiga = pessoa.get().getMesa();
				mesaAntiga.aumentarCadeirasDisponiveis();

				Mesa mesaNova = mesaRepository.getOne(this.idMesa);
				mesaNova.diminuirCadeirasDisponiveis();
				pessoa.get().setNome(this.nome);
				pessoa.get().setGrupo(grupoRepository.getOne(this.idGrupo));
				pessoa.get().setMesa(mesaRepository.getOne(this.idMesa));
			}

		} else {
			throw new Exception("Nao há lugares disponiveis para a mesa escolhida.");
		}
		return pessoa;
	}

}
