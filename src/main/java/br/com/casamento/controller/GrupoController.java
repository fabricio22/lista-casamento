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

import br.com.casamento.modelo.Grupo;
import br.com.casamento.repository.GrupoRepository;
import br.com.casamento.vo.entrada.GrupoEntradaVO;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping
	public List<Grupo> listaGrupo() {

		List<Grupo> resultado = grupoRepository.findAll();
		return resultado;
	}

	@PostMapping
	public ResponseEntity<GrupoEntradaVO> cadastrar(@RequestBody @Validated GrupoEntradaVO nomeGrupo, UriComponentsBuilder uriBuilder) {

		Grupo grupo = nomeGrupo.converter(nomeGrupo);
		grupoRepository.save(grupo);
		URI uri = uriBuilder.path("/grupo/{id}").buildAndExpand(grupo.getId()).toUri();
		return ResponseEntity.created(uri).body(new GrupoEntradaVO(grupo));
	}

}
