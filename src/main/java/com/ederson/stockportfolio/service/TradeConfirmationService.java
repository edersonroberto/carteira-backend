package com.ederson.stockportfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ederson.stockportfolio.dto.DetalheNotaDto;
import com.ederson.stockportfolio.dto.ListarNotaDto;
import com.ederson.stockportfolio.dto.TradeConfirmationDto;
import com.ederson.stockportfolio.dto.TradeConfirmationFilter;
import com.ederson.stockportfolio.enums.OperationType;
import com.ederson.stockportfolio.factory.TradeConfirmationFactory;
import com.ederson.stockportfolio.model.TradeConfirmation;
import com.ederson.stockportfolio.repository.TradeConfirmationRepository;
import com.ederson.stockportfolio.specification.SpecificationNota;

@Service
public class TradeConfirmationService {
	
	@Autowired
	TradeConfirmationRepository notaRepository;
	
	private TradeConfirmationFactory notaFactory;

	public List<ListarNotaDto> listar(OperationType operacao) {
		List<TradeConfirmation> notas = notaRepository.findByOperationType(operacao, Sort.by(Sort.Direction.DESC, "data"));
		return notas.stream().map(nota -> getNotaFactory().toListarNotaDto(nota))
				.collect(Collectors.toList());
	}
	
	public List<ListarNotaDto> listarNotas(TradeConfirmationFilter notaFilter) {
		
		List<TradeConfirmation> notas = notaRepository.findAll(Specification
				.where(
						SpecificationNota.corretora(notaFilter.getIdCorretora())
						//.and(SpecificationNota.corretora(notaFilter.getIdCorretora()))
						.and(SpecificationNota.operacao(notaFilter.getTipoOperacao()))
				));

		return notas.stream().map(nota -> getNotaFactory().toListarNotaDto(nota))
				.collect(Collectors.toList());
	}

	public TradeConfirmation inserir(TradeConfirmationDto notaDto) {
		
		TradeConfirmation notaExistente = notaRepository
				.findByDataAndValorAndAtivoIdAndCorretoraId(notaDto.getData(), notaDto.getValue()
						, notaDto.getIdAtivo(), notaDto.getIdCorretora());
		
		if (notaExistente != null) {
			throw new RuntimeException("Nota já cadastrada no sistema");
		}
		
		TradeConfirmation nota = getNotaFactory().toNota(notaDto);
		return notaRepository.save(nota);
	}
	
	private TradeConfirmationFactory getNotaFactory(){
		if (notaFactory == null) {
			notaFactory = new TradeConfirmationFactory();
		}
		return notaFactory;
	}

	public void excluir(Long id) {
		notaRepository.deleteById(id);
	}

	public DetalheNotaDto detalhar(Long id) {
		Optional<TradeConfirmation> nota = notaRepository.findById(id);
		return getNotaFactory().toDetalheNotaDto(nota.get());
	}

	public TradeConfirmation editar(TradeConfirmationDto notaDto, Long id) {
		Optional<TradeConfirmation> nota = notaRepository.findById(id);
		TradeConfirmation notaUpdate = nota.orElseThrow();
		
		getNotaFactory().toNota(notaDto, notaUpdate);
		notaRepository.flush();
	
		return notaUpdate;
	}



}
