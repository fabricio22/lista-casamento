package br.com.casamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.casamento.modelo.Grupo;
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.validation.PessoaControllerExceptionHandler;
import br.com.casamento.validation.PessoaNotFoundException;
import br.com.casamento.vo.entrada.AtualizaGrupoEntradaVO;
import br.com.casamento.vo.entrada.GrupoEntradaVO;
import br.com.casamento.vo.saida.GrupoSaidaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/grupo")
@Api(description = "API de controle de Grupo")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping
	public List<Grupo> listaGrupo() {

		List<Grupo> resultado = grupoRepository.findAll();
		return resultado;
	}

	@PostMapping
	public ResponseEntity<GrupoEntradaVO> cadastrar(@RequestBody @Validated GrupoEntradaVO nomeGrupo,
			UriComponentsBuilder uriBuilder) {

		Grupo grupo = nomeGrupo.converter(nomeGrupo);
		grupoRepository.save(grupo);
		URI uri = uriBuilder.path("/grupo/{id}").buildAndExpand(grupo.getId()).toUri();
		return ResponseEntity.created(uri).body(new GrupoEntradaVO(grupo));
	}

	@GetMapping("/{id}")
	@ExceptionHandler(value = PessoaNotFoundException.class)
	public ResponseEntity<GrupoSaidaVO> getGrupoByid(@PathVariable Long id) {

		Optional<Grupo> grupo = grupoRepository.findById(id);

		if (grupo.isPresent()) {
			return ResponseEntity.ok().body(new GrupoSaidaVO(grupo.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<GrupoSaidaVO> atualizarGrupo(@PathVariable Long id,
			@RequestBody @Valid AtualizaGrupoEntradaVO dadosAtualizacao) {
		Grupo grupo = dadosAtualizacao.atualizar(id, grupoRepository);
		return ResponseEntity.ok(new GrupoSaidaVO(grupo));
	}

}
