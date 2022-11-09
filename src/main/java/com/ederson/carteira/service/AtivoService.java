package com.ederson.carteira.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.carteira.dto.AtivoDto;
import com.ederson.carteira.dto.DetalheAtivoDto;
import com.ederson.carteira.dto.TicketDto;
import com.ederson.carteira.factory.AtivoFactory;
import com.ederson.carteira.model.Administradora;
import com.ederson.carteira.model.Ativo;
import com.ederson.carteira.model.Nota;
import com.ederson.carteira.model.Pregao;
import com.ederson.carteira.repository.AdministradoraRepository;
import com.ederson.carteira.repository.AtivoRepository;
import com.ederson.carteira.repository.NotaRepository;

@Service
public class AtivoService {

	@Autowired
	AtivoRepository ativoRepository;

	@Autowired
	AdministradoraRepository administradoraRepository;

	@Autowired
	NotaRepository notaRepository;
	
	@Autowired
	PregaoService pregaoService;

	private AtivoFactory ativoFactory;

	public List<Ativo> listar() {
		return ativoRepository.findAll();
	}

	public List<TicketDto> listarTickets() {
		List<Ativo> list = ativoRepository.findAll();
		return list.stream().map(getAtivoFactory()::toTicketDto).collect(Collectors.toList());
	}

	private AtivoFactory getAtivoFactory() {
		if (ativoFactory == null) {
			ativoFactory = new AtivoFactory();
		}
		return ativoFactory;
	}

	public Ativo incluir(AtivoDto ativoDto) {
		Administradora administradora = administradoraRepository.findByCnpj(ativoDto.getAdministradora().getCnpj());
		Ativo ativo = getAtivoFactory().toAtivo(ativoDto);
		ativo.setAdministradora(administradora);
		return ativoRepository.save(ativo);
	}

	public DetalheAtivoDto detalharAtivo(String cnpj) {
		List<Nota> notas = notaRepository.findByAtivoCnpj(cnpj);
		if (notas.isEmpty()) {
			return new DetalheAtivoDto();
		}
		return getAtivoFactory().toDetalheAtivo(notas);
	}

	public List<DetalheAtivoDto> detalharCarteira() {
		List<Ativo> ativos = ativoRepository.findAll();
		
		List<DetalheAtivoDto> carteiraAtivos = new ArrayList<>();
		
		ativos.forEach(ativo -> {
			List<Nota> notas = notaRepository.findByAtivoCnpj(ativo.getCnpj());
			if (!notas.isEmpty()) {
				DetalheAtivoDto detalheAtivo = getAtivoFactory().toDetalheAtivo(notas);
				if (detalheAtivo.getQuantidade() > 0) {	
					Pregao ultimoPregao = pregaoService.buscaUltimoPregao(ativo.getId());
					detalheAtivo.setValorCota(ultimoPregao != null ? ultimoPregao.getValorFechamento() : BigDecimal.ZERO);
					carteiraAtivos.add(detalheAtivo);
				}
			}
			
		});
		
		return carteiraAtivos;
	}
}
