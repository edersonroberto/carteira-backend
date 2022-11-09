package com.ederson.carteira.factory;

import com.ederson.carteira.dto.DetalheNotaDto;
import com.ederson.carteira.dto.ListarNotaDto;
import com.ederson.carteira.dto.NotaDto;
import com.ederson.carteira.model.Ativo;
import com.ederson.carteira.model.Corretora;
import com.ederson.carteira.model.Nota;

public class NotaFactory {

	public Nota toNota(NotaDto notaDto) {
		return Nota.builder().ativo(new Ativo(notaDto.getIdAtivo())).corretora(new Corretora(notaDto.getIdCorretora()))
				.taxa(notaDto.getTaxa()).emolumento(notaDto.getEmolumento()).corretagem(notaDto.getCorretagem())
				.iss(notaDto.getIss()).data(notaDto.getData()).valor(notaDto.getValor())
				.quantidade(notaDto.getQuantidade()).operacao(notaDto.getTipoOperacao())
				// .custoTotal(notaDto.getCustoTotal())
				.build();
	}

	public ListarNotaDto toListarNotaDto(Nota nota) {
		return ListarNotaDto.builder().id(nota.getId()).idCorretora(nota.getCorretora().getId()).data(nota.getData())
				.valor(nota.getValor()).quantidade(nota.getQuantidade()).taxa(nota.getTaxa())
				.emolumento(nota.getEmolumento()).corretagem(nota.getCorretagem()).ticket(nota.getAtivo().getTicket())
				.tipoOperacao(nota.getOperacao()).iss(nota.getIss()).build();
	}

	public DetalheNotaDto toDetalheNotaDto(Nota nota) {
		return DetalheNotaDto.builder().data(nota.getData()).emolumento(nota.getEmolumento())
				.nomeCorretora(nota.getCorretora().getNome()).quantidade(nota.getQuantidade()).taxa(nota.getTaxa())
				.ticket(nota.getAtivo().getTicket()).tipoOperacao(nota.getOperacao()).valor(nota.getValor())
				.custoTotal(nota.getCustoTotal()).build();
	}

	public void toNota(NotaDto notaDto, Nota nota) {
		nota.setAtivo(new Ativo(notaDto.getIdAtivo()));
		nota.setCorretagem(notaDto.getCorretagem());
		nota.setCorretora(new Corretora(notaDto.getIdCorretora()));
		nota.setData(notaDto.getData());
		nota.setEmolumento(notaDto.getEmolumento());
		nota.setOperacao(notaDto.getTipoOperacao());
		nota.setQuantidade(notaDto.getQuantidade());
		nota.setIss(notaDto.getIss());
		nota.setTaxa(notaDto.getTaxa());
		nota.setValor(notaDto.getValor());
	}

}
