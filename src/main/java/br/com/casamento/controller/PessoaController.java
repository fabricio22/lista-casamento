package br.com.casamento.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.casamento.modelo.Grupo;
import br.com.casamento.modelo.Mesa;
import br.com.casamento.modelo.Pessoa;
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.repository.PessoaRepository;
import br.com.casamento.vo.entrada.AtualizarPessoaEntradaVO;
import br.com.casamento.vo.entrada.PessoaEntradaVO;
import br.com.casamento.vo.saida.PessoaSaidaVO;
import br.com.casamento.vo.saida.PessoasVO;

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
	public PessoasVO getTodasPessoas(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Page<Pessoa> pagePessoa = pessoaRepository
				.findAll(PageRequest.of(page, size, Sort.by(Direction.fromString(direction), orderBy)));

		PessoasVO totalPessoasVO = new PessoasVO();
		totalPessoasVO.setPessoas(pagePessoa.getContent());
		totalPessoasVO.setTotalPages(pagePessoa.getTotalPages());
		totalPessoasVO.setTotal(pagePessoa.getTotalElements());

		return totalPessoasVO;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Validated PessoaEntradaVO pessoaVoEntrada,
			UriComponentsBuilder uriBuilder) {

		Optional<Grupo> grupo = grupoRepository.findById(pessoaVoEntrada.getCodigoGrupo());
		if (!grupo.isPresent())
			return new ResponseEntity<>("Grupo não encontrado.", HttpStatus.NOT_FOUND);

		Optional<Mesa> mesa = mesaRepository.findById(pessoaVoEntrada.getNumeroMesa());
		if (!mesa.isPresent())
			return new ResponseEntity<>("Mesa não encontrada.", HttpStatus.NOT_FOUND);

		if (mesa.get().getTemLugarDisponivel()) {

			Pessoa pessoa = pessoaVoEntrada.converter(pessoaVoEntrada, grupo.get(), mesa.get());
			mesa.get().diminuirCadeirasDisponiveis();

			pessoaRepository.save(pessoa);

			URI uri = uriBuilder.path("pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(new PessoaSaidaVO(pessoa));
		}

		return new ResponseEntity<>("Não há lugares disponiveis.", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPessoaByid(@PathVariable Long id) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(id);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok().body(new PessoaSaidaVO(pessoa.get()));
		} else {
			return new ResponseEntity<>("Pessoa não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody AtualizarPessoaEntradaVO dadosAtualizacao)
			throws Exception {

		Optional<Pessoa> pessoa = dadosAtualizacao.atualizar(id, pessoaRepository, mesaRepository, grupoRepository);

		if (!pessoa.isPresent()) {
			return new ResponseEntity<>("Pessoa não encontrada.", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new PessoaSaidaVO(pessoa.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removePessoas(@PathVariable Long id) {
		try {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().body("Pessoa removida com sucesso.");
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Pessoa não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

}
