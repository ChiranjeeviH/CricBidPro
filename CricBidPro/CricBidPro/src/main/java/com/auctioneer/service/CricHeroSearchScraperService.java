package com.auctioneer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class CricHeroSearchScraperService {
    private final WebClient webClient;

    private final static String BASEURL = "https://cricheroes.in/";
    private final static String GLOBAL_SEARCH="api/v1/other/global-search/";

    private final static String PLAYER_PROFILE="player-profile";

    @Autowired
    public CricHeroSearchScraperService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASEURL).build();
    }

    private String getRequest(String endpointUri)  {
        return this.webClient.get()
                .uri(endpointUri)
                .headers(httpHeaders -> httpHeaders.addAll(buildCustomHeaders()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getUserInfo(String cricheroName) {
        String searchEndpoint = GLOBAL_SEARCH + URLEncoder.encode(cricheroName, StandardCharsets.UTF_8) + "?pagesize=12";

        return getRequest(searchEndpoint);
    }

    public Mono<String> getUserFullProfile(String cricheroName){

        return null;
    }

    private HttpHeaders buildCustomHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Api-Key", "cr!CkH3r0s");
        headers.add("Device-Type", "Firefox: 119.0");
        headers.add("Host", "cricheroes.in");
        headers.add("Udid", "ab7905ae4e2ddf11cc91ca0e05241cfc");
        return headers;
    }


}
