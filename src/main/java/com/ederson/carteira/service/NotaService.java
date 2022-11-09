package com.ederson.carteira.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ederson.carteira.dto.DetalheNotaDto;
import com.ederson.carteira.dto.ListarNotaDto;
import com.ederson.carteira.dto.NotaDto;
import com.ederson.carteira.dto.NotaFilter;
import com.ederson.carteira.enums.TipoOperacao;
import com.ederson.carteira.factory.NotaFactory;
import com.ederson.carteira.model.Nota;
import com.ederson.carteira.repository.NotaRepository;
import com.ederson.carteira.specification.SpecificationNota;

@Service
public class NotaService {
	
	@Autowired
	NotaRepository notaRepository;
	
	private NotaFactory notaFactory;

	public List<ListarNotaDto> listar(TipoOperacao operacao) {
		List<Nota> notas = notaRepository.findByOperacao(operacao, Sort.by(Sort.Direction.DESC, "data"));
		return notas.stream().map(nota -> getNotaFactory().toListarNotaDto(nota))
				.collect(Collectors.toList());
	}
	
	public List<ListarNotaDto> listarNotas(NotaFilter notaFilter) {
		
		List<Nota> notas = notaRepository.findAll(Specification
				.where(
						SpecificationNota.corretora(notaFilter.getIdCorretora())
						//.and(SpecificationNota.corretora(notaFilter.getIdCorretora()))
						.and(SpecificationNota.operacao(notaFilter.getTipoOperacao()))
				));

		return notas.stream().map(nota -> getNotaFactory().toListarNotaDto(nota))
				.collect(Collectors.toList());
	}

	public Nota inserir(NotaDto notaDto) {
		
		Nota notaExistente = notaRepository
				.findByDataAndValorAndAtivoIdAndCorretoraId(notaDto.getData(), notaDto.getValor()
						, notaDto.getIdAtivo(), notaDto.getIdCorretora());
		
		if (notaExistente != null) {
			throw new RuntimeException("Nota já cadastrada no sistema");
		}
		
		Nota nota = getNotaFactory().toNota(notaDto);
		return notaRepository.save(nota);
	}
	
	private NotaFactory getNotaFactory(){
		if (notaFactory == null) {
			notaFactory = new NotaFactory();
		}
		return notaFactory;
	}

	public void excluir(Long id) {
		notaRepository.deleteById(id);
	}

	public DetalheNotaDto detalhar(Long id) {
		Optional<Nota> nota = notaRepository.findById(id);
		return getNotaFactory().toDetalheNotaDto(nota.get());
	}

	public Nota editar(NotaDto notaDto, Long id) {
		Optional<Nota> nota = notaRepository.findById(id);
		Nota notaUpdate = nota.orElseThrow();
		
		getNotaFactory().toNota(notaDto, notaUpdate);
		notaRepository.flush();
	
		return notaUpdate;
	}



}
