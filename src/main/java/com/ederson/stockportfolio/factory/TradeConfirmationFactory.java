package com.ederson.stockportfolio.factory;

import com.ederson.stockportfolio.dto.DetalheNotaDto;
import com.ederson.stockportfolio.dto.ListarNotaDto;
import com.ederson.stockportfolio.dto.TradeConfirmationDto;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.BrokerageFirm;
import com.ederson.stockportfolio.model.TradeConfirmation;

public class TradeConfirmationFactory {

	public TradeConfirmation toNota(TradeConfirmationDto notaDto) {
		return TradeConfirmation.builder().asset(new Asset(notaDto.getIdAtivo())).brokerageFirm(new BrokerageFirm(notaDto.getIdCorretora()))
				.clearingFee(notaDto.getTaxa()).exchangeFee(notaDto.getEmolumento()).corretagem(notaDto.getCorretagem())
				.iss(notaDto.getIss()).data(notaDto.getData()).value(notaDto.getValue())
				.amount(notaDto.getAmount()).operationType(notaDto.getOperationType())
				// .custoTotal(notaDto.getCustoTotal())
				.build();
	}

	public ListarNotaDto toListarNotaDto(TradeConfirmation nota) {
		return ListarNotaDto.builder().id(nota.getId()).idCorretora(nota.getBrokerageFirm().getId()).data(nota.getData())
				.valor(nota.getValue()).quantidade(nota.getAmount()).taxa(nota.getClearingFee())
				.emolumento(nota.getExchangeFee()).corretagem(nota.getCorretagem()).ticket(nota.getAsset().getTicket())
				.tipoOperacao(nota.getOperationType()).iss(nota.getIss()).build();
	}

	public DetalheNotaDto toDetalheNotaDto(TradeConfirmation nota) {
		return DetalheNotaDto.builder().data(nota.getData()).emolumento(nota.getExchangeFee())
				.nomeCorretora(nota.getBrokerageFirm().getName()).quantidade(nota.getAmount()).taxa(nota.getClearingFee())
				.ticket(nota.getAsset().getTicket()).tipoOperacao(nota.getOperationType()).valor(nota.getValue())
				.custoTotal(nota.getCustoTotal()).build();
	}

	public void toNota(TradeConfirmationDto notaDto, TradeConfirmation nota) {
		nota.setAsset(new Asset(notaDto.getIdAtivo()));
		nota.setCorretagem(notaDto.getCorretagem());
		nota.setBrokerageFirm(new BrokerageFirm(notaDto.getIdCorretora()));
		nota.setData(notaDto.getData());
		nota.setExchangeFee(notaDto.getEmolumento());
		nota.setOperationType(notaDto.getOperationType());
		nota.setAmount(notaDto.getAmount());
		nota.setIss(notaDto.getIss());
		nota.setClearingFee(notaDto.getTaxa());
		nota.setValue(notaDto.getValue());
	}

}
