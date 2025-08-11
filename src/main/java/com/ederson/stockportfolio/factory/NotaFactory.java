package com.ederson.stockportfolio.factory;

import com.ederson.stockportfolio.dto.DetalheNotaDto;
import com.ederson.stockportfolio.dto.ListarNotaDto;
import com.ederson.stockportfolio.dto.NotaDto;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.BrokerageFirm;
import com.ederson.stockportfolio.model.Nota;

public class NotaFactory {

	public Nota toNota(NotaDto notaDto) {
		return Nota.builder().asset(new Asset(notaDto.getIdAtivo())).corretora(new BrokerageFirm(notaDto.getIdCorretora()))
				.taxa(notaDto.getTaxa()).exchangeFee(notaDto.getEmolumento()).corretagem(notaDto.getCorretagem())
				.iss(notaDto.getIss()).data(notaDto.getData()).value(notaDto.getValor())
				.amount(notaDto.getQuantidade()).operationType(notaDto.getTipoOperacao())
				// .custoTotal(notaDto.getCustoTotal())
				.build();
	}

	public ListarNotaDto toListarNotaDto(Nota nota) {
		return ListarNotaDto.builder().id(nota.getId()).idCorretora(nota.getCorretora().getId()).data(nota.getData())
				.valor(nota.getValue()).quantidade(nota.getAmount()).taxa(nota.getTaxa())
				.emolumento(nota.getExchangeFee()).corretagem(nota.getCorretagem()).ticket(nota.getAsset().getTicket())
				.tipoOperacao(nota.getOperationType()).iss(nota.getIss()).build();
	}

	public DetalheNotaDto toDetalheNotaDto(Nota nota) {
		return DetalheNotaDto.builder().data(nota.getData()).emolumento(nota.getExchangeFee())
				.nomeCorretora(nota.getCorretora().getNome()).quantidade(nota.getAmount()).taxa(nota.getTaxa())
				.ticket(nota.getAsset().getTicket()).tipoOperacao(nota.getOperationType()).valor(nota.getValue())
				.custoTotal(nota.getCustoTotal()).build();
	}

	public void toNota(NotaDto notaDto, Nota nota) {
		nota.setAsset(new Asset(notaDto.getIdAtivo()));
		nota.setCorretagem(notaDto.getCorretagem());
		nota.setCorretora(new BrokerageFirm(notaDto.getIdCorretora()));
		nota.setData(notaDto.getData());
		nota.setExchangeFee(notaDto.getEmolumento());
		nota.setOperationType(notaDto.getTipoOperacao());
		nota.setAmount(notaDto.getQuantidade());
		nota.setIss(notaDto.getIss());
		nota.setTaxa(notaDto.getTaxa());
		nota.setValue(notaDto.getValor());
	}

}
