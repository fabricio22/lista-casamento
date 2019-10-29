package br.com.casamento.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.vo.entrada.AtualizaGrupoEntradaVO;
import br.com.casamento.vo.entrada.GrupoEntradaVO;
import br.com.casamento.vo.saida.GrupoSaidaVO;
import br.com.casamento.vo.saida.GrupoVO;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/grupo")
@Api(description = "API de controle de Grupo")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping
	public GrupoVO listaGrupo(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Page<Grupo> pageGrupo = grupoRepository
				.findAll(PageRequest.of(page, size, Sort.by(Direction.fromString(direction), orderBy)));

		GrupoVO grupoVO = new GrupoVO();
		grupoVO.setGrupo(pageGrupo.getContent());
		grupoVO.setTotal(pageGrupo.getTotalElements());
		grupoVO.setTotalPages(pageGrupo.getTotalPages());

		return grupoVO;
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerGrupo(@PathVariable Long id) {
		try {
			grupoRepository.deleteById(id);
			return ResponseEntity.ok().body("Grupo removido com sucesso.");
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Grupo não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

}
