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

import br.com.casamento.modelo.Mesa;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.vo.entrada.AtualizaMesaEntradaVO;
import br.com.casamento.vo.entrada.MesaEntradaVO;
import br.com.casamento.vo.saida.MesaSaidaVO;
import br.com.casamento.vo.saida.MesaVO;

@RestController
@RequestMapping("/mesa")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping
	public MesaVO getTodasMesas(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Page<Mesa> pageMesa = mesaRepository
				.findAll(PageRequest.of(page, size, Sort.by(Direction.fromString(direction), orderBy)));

		MesaVO mesaVO = new MesaVO();
		mesaVO.setMesa(pageMesa.getContent());
		mesaVO.setTotal(pageMesa.getTotalElements());
		mesaVO.setTotalPages(pageMesa.getTotalPages());

		return mesaVO;
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerMesa(@PathVariable Long id) {
		try {
			mesaRepository.deleteById(id);
			return ResponseEntity.ok("Mesa removida com sucesso.");
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>("Mesa não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

}
