package com.ederson.stockportfolio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.dto.AtivoDto;
import com.ederson.stockportfolio.dto.DetalheAtivoDto;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.exceptions.AdministradoraNaoEncontradaException;
import com.ederson.stockportfolio.exceptions.CarteiraException;
import com.ederson.stockportfolio.factory.AtivoFactory;
import com.ederson.stockportfolio.model.Administrator;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.Nota;
import com.ederson.stockportfolio.model.Pregao;
import com.ederson.stockportfolio.repository.AdministradoraRepository;
import com.ederson.stockportfolio.repository.AtivoRepository;
import com.ederson.stockportfolio.repository.NotaRepository;

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

	public List<Asset> listar() {
		return ativoRepository.findAll();
	}

	public List<TicketDto> listarTickets() {
		List<Asset> list = ativoRepository.findAll();
		return list.stream().map(getAtivoFactory()::toTicketDto).collect(Collectors.toList());
	}

	private AtivoFactory getAtivoFactory() {
		if (ativoFactory == null) {
			ativoFactory = new AtivoFactory();
		}
		return ativoFactory;
	}

	public Asset incluir(AtivoDto ativoDto) throws CarteiraException {
		Administrator administrator = administradoraRepository.findByCnpj(ativoDto.getCnpjAdministrator());
		if (Objects.isNull(administrator)) {
			throw new AdministradoraNaoEncontradaException(
					String.format("Administradora com cnpj [%s] não encontrada.", ativoDto.getCnpjAdministrator()));
		}

		Asset ativo = getAtivoFactory().toAtivo(ativoDto);
		ativo.setAdministrator(administrator);
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
		List<Asset> ativos = ativoRepository.findAll();
		
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
