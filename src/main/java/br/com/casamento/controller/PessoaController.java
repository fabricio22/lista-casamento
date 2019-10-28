package br.com.casamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.casamento.modelo.Grupo;
import br.com.casamento.modelo.Mesa;
import br.com.casamento.modelo.Pessoa;
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.repository.PessoaRepository;
import br.com.casamento.validation.PessoaNotFoundException;
import br.com.casamento.vo.entrada.AtualizarPessoaEntradaVO;
import br.com.casamento.vo.entrada.PessoaEntradaVO;
import br.com.casamento.vo.saida.PessoaSaidaVO;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping
	public List<Pessoa> getTodasPessoas() {

		List<Pessoa> listapessoas = pessoaRepository.findAll();
		return listapessoas;
	}

	@PostMapping
	public ResponseEntity<PessoaSaidaVO> cadastrar(@RequestBody @Validated PessoaEntradaVO pessoaVoEntrada,
			UriComponentsBuilder uriBuilder) {

		Grupo grupo = grupoRepository.findById(pessoaVoEntrada.getCodigoGrupo()).get();
		Mesa mesa = mesaRepository.findByid(pessoaVoEntrada.getNumeroMesa());
		Pessoa pessoa = null;

		if (mesa.getTemLugarDisponivel()) {

			pessoa = pessoaVoEntrada.converter(pessoaVoEntrada, grupo, mesa);
			try {
				mesa.diminuirCadeirasDisponiveis();
			} catch (Exception e) {
				e.printStackTrace();
			}

			mesaRepository.save(mesa);
			pessoaRepository.save(pessoa);
		}

		URI uri = uriBuilder.path("pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaSaidaVO(pessoa));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaSaidaVO> getPessoaByid(@PathVariable Long id) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if (pessoa.isPresent()) {
			return ResponseEntity.ok().body(new PessoaSaidaVO(pessoa.get()));
		}else {
			throw new PessoaNotFoundException();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaSaidaVO> atualizar(@PathVariable Long id,
			@RequestBody AtualizarPessoaEntradaVO dadosAtualizacao) throws Exception {

		Pessoa pessoa;
		pessoa = dadosAtualizacao.atualizar(id, pessoaRepository, mesaRepository, grupoRepository);
		return ResponseEntity.ok(new PessoaSaidaVO(pessoa));
	}
}
