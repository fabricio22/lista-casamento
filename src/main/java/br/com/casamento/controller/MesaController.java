package br.com.casamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.casamento.modelo.Mesa;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.vo.entrada.AtualizaMesaEntradaVO;
import br.com.casamento.vo.entrada.MesaEntradaVO;
import br.com.casamento.vo.saida.MesaSaidaVO;

@RestController
@RequestMapping("/mesa")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping
	public List<Mesa> getTodasMesas() {
		List<Mesa> listaMesas = mesaRepository.findAll();
		return listaMesas;
	}

	@PostMapping
	public ResponseEntity<MesaEntradaVO> cadastrar(@RequestBody @Validated MesaEntradaVO mesaVo,
			UriComponentsBuilder uriBuilder) {

		Mesa mesa = mesaVo.converter(mesaVo);
		mesaRepository.save(mesa);
		URI uri = uriBuilder.path("/mesa/{id}").buildAndExpand(mesa.getId()).toUri();
		return ResponseEntity.created(uri)
				.body(new MesaEntradaVO(mesa.getId(), mesa.getQuantidadeCadeirasDisponiveis()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MesaSaidaVO> getMesaByid(@PathVariable Long id) {

		Optional<Mesa> mesa = mesaRepository.findById(id);

		if (mesa.isPresent()) {
			return ResponseEntity.ok().body(new MesaSaidaVO(mesa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MesaSaidaVO> atualizaMesa(@PathVariable Long id,
			@RequestBody AtualizaMesaEntradaVO dadosAtualizacao) {

		Mesa mesa = dadosAtualizacao.atualizar(id, mesaRepository);

		return ResponseEntity.ok(new MesaSaidaVO(mesa));
	}

}
