package br.com.casamento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.casamento.modelo.Pessoa;
import br.com.casamento.repository.PessoaRepository;
import br.com.casamento.vo.PessoaVo;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> getTodasPessoas() {

		List<Pessoa> listapessoas = pessoaRepository.findAll();
		return listapessoas;
	}

	@PostMapping
	public ResponseEntity<PessoaVo> cadastrar(@RequestBody @Validated PessoaVo pessoaVo, UriComponentsBuilder uriBuilder) {

		Pessoa pessoa = pessoaVo.converter(pessoaVo);
		pessoaRepository.save(pessoa);
		URI uri = uriBuilder.path("pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaVo(pessoa));

	}

}
