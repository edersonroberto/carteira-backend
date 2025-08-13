package com.ederson.stockportfolio.schedule;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ederson.stockportfolio.config.PropertiesConfig;
import com.ederson.stockportfolio.dto.TicketDto;
import com.ederson.stockportfolio.model.Asset;
import com.ederson.stockportfolio.model.TradingSession;
import com.ederson.stockportfolio.service.AssetService;
import com.ederson.stockportfolio.service.TradingSessionService;

@Component
public class TradingSessionSchedule {

	@Autowired
	AssetService assetService;
	
	@Autowired
	TradingSessionService tradingSessionService;
	
	@Autowired
	private PropertiesConfig properties;
	
	@Scheduled(cron = "0 15 9 * * ?")
	public void executar() {
		List<TicketDto> tickets = assetService.listTickets();
		
		tickets.forEach(ticket -> {
			var object = buscaPregaoAtivo(ticket.getName());
			
			BigDecimal openingValue = new BigDecimal(object.get("vl_abertura").toString());
			BigDecimal closingValue = new BigDecimal(object.get("vl_fechamento").toString());
			BigDecimal minimumValue = new BigDecimal(object.get("vl_minimo").toString());
			BigDecimal maximumvalue = new BigDecimal(object.get("vl_maximo").toString());
			String stringData = object.get("dt_pregao").toString();
			
			LocalDate data = LocalDate.of(Integer.valueOf(stringData.substring(0, 4)), Integer.valueOf(stringData.substring(4, 6)), Integer.valueOf(stringData.substring(6, 8)));
			
			TradingSession pregao = TradingSession.builder().asset(new Asset(ticket.getId()))
					.openingPrice(openingValue).closingPrice(closingValue)
					.valorMaximo(maximumvalue).valorMinimo(minimumValue)
					.tradingSessionDate(data).build();
			
			tradingSessionService.insert(pregao);
			
		});
	}
	
	private JSONObject buscaPregaoAtivo(String ticket) {
		
		String url = properties.getApiCotacao();
		try {
			URI uri = new URI(url + ticket + "/1");
			System.out.println("Url: " + uri);
			HttpRequest request = HttpRequest.newBuilder().uri(uri)
					.GET().build();

			HttpResponse<String> response = HttpClient.newHttpClient().send(request,
					HttpResponse.BodyHandlers.ofString());

			String body = response.body();
			
			String json = body.substring(1, body.lastIndexOf("]"));
			return new JSONObject(json);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

}
