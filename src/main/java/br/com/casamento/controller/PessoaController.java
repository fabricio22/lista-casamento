package br.com.casamento.controller;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import br.com.casamento.vo.entrada.PessoaEntradaVO;
import br.com.casamento.vo.saida.PessoaVoSaida;

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
	public ResponseEntity<PessoaVoSaida> cadastrar(@RequestBody @Validated PessoaEntradaVO pessoaVoEntrada,
			UriComponentsBuilder uriBuilder) {

		Grupo grupo = grupoRepository.findById(pessoaVoEntrada.getCodigoGrupo()).get();
		Mesa mesa = mesaRepository.findByid(pessoaVoEntrada.getNumeroMesa());
		Pessoa pessoa = null;

		if (mesa.getTemLugarDisponivel(mesa.getQuantidadeCadeirasDisponiveis())) {

			pessoa = pessoaVoEntrada.converter(pessoaVoEntrada, grupo, mesa);
			try {
				mesa.setQuantidadeCadeirasDisponiveis(mesa.getQuantidadeCadeirasDisponiveis() - 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mesaRepository.save(mesa);
			pessoaRepository.save(pessoa);
		}
		
		URI uri = uriBuilder.path("pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaVoSaida(pessoa));
	}
	
	@GetMapping("/pesquisa-por-nome-convidado")
	@SuppressWarnings("unchecked")
	public PessoaVoSaida getPessoaByNome(@RequestParam(name = "convidado") String nome){
		Pessoa pessoa = pessoaRepository.findByNome(nome);
		PessoaVoSaida pessoaVoSaida = new PessoaVoSaida(pessoa);
		return pessoaVoSaida;
	}
}
