package br.com.santospage.taxassistant.application.services;

import br.com.santospage.taxassistant.interfaces.dto.TaxPredictionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TaxPredictionService {

    private final WebClient webClient;

    public TaxPredictionService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<TaxPredictionDTO> predict(
            TaxPredictionDTO request,
            int page,
            int size
    ) {
        if (request == null) {
            throw new IllegalArgumentException("Request body must not be null");
        }

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/predict")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build()
                )
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(TaxPredictionDTO.class)
                .collectList()
                .block();
    }
}

