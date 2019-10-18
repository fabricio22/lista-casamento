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

import br.com.casamento.modelo.Mesa;
import br.com.casamento.repository.MesaRepository;
import br.com.casamento.vo.MesaVo;


@RestController
@RequestMapping("/mesa")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;
	
	@GetMapping
	public List<Mesa> getTodasMesas(){
		List<Mesa> listaMesas = mesaRepository.findAll();
		return listaMesas;
	}
	
	@PostMapping
	public ResponseEntity<MesaVo> cadastrar(@RequestBody @Validated MesaVo mesaVo, UriComponentsBuilder uriBuilder) {
		
		Mesa mesa = mesaVo.converter(mesaVo);
		System.out.println("Mesa id: " + mesa.getId());
		System.out.println("Quantidade: " + mesa.getQuantidadeCadeiras());
		System.out.println("Disponivel: " + mesa.getQuantidadeCadeirasDisponiveis());
		mesaRepository.save(mesa);	
		URI uri = uriBuilder.path("/mesa/{id}").buildAndExpand(mesa.getId()).toUri();
		return ResponseEntity.created(uri).body(new MesaVo(mesa.getId(), mesa.getQuantidadeCadeirasDisponiveis()));
		
	}
	
}
