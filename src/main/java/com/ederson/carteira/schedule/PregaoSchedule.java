package com.ederson.carteira.schedule;

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

import com.ederson.carteira.config.PropertiesConfig;
import com.ederson.carteira.dto.TicketDto;
import com.ederson.carteira.model.Ativo;
import com.ederson.carteira.model.Pregao;
import com.ederson.carteira.service.AtivoService;
import com.ederson.carteira.service.PregaoService;

@Component
public class PregaoSchedule {

	@Autowired
	AtivoService ativoService;
	
	@Autowired
	PregaoService pregaoService;
	
	@Autowired
	private PropertiesConfig properties;
	
	@Scheduled(cron = "0 15 9 * * ?")
	public void executar() {
		List<TicketDto> tickets = ativoService.listarTickets();
		
		tickets.forEach(ticket -> {
			var object = buscaPregaoAtivo(ticket.getNome());
			
			BigDecimal valorAbertura = new BigDecimal(object.get("vl_abertura").toString());
			BigDecimal valorFechamento = new BigDecimal(object.get("vl_fechamento").toString());
			BigDecimal valorMinimo = new BigDecimal(object.get("vl_minimo").toString());
			BigDecimal valorMaximo = new BigDecimal(object.get("vl_maximo").toString());
			String stringData = object.get("dt_pregao").toString();
			
			LocalDate data = LocalDate.of(Integer.valueOf(stringData.substring(0, 4)), Integer.valueOf(stringData.substring(4, 6)), Integer.valueOf(stringData.substring(6, 8)));
			
			Pregao pregao = Pregao.builder().ativo(new Ativo(ticket.getId()))
					.valorAbertura(valorAbertura).valorFechamento(valorFechamento)
					.valorMaximo(valorMaximo).valorMinimo(valorMinimo)
					.dataPregao(data).build();
			
			pregaoService.inserir(pregao);
			
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
