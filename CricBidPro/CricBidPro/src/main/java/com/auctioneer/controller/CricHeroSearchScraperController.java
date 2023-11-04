package com.auctioneer.controller;

import com.auctioneer.entity.Player;
import com.auctioneer.service.PlayerScaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/scrapper")
public class CricHeroSearchScraperController {
	private final PlayerScaperService playerScaperService;

  private final String API_ENDPOINT = "https://cricheroes.in/api/v1/other/global-search/";

    public CricHeroSearchScraperController(PlayerScaperService playerScaperService) {
        this.playerScaperService = playerScaperService;
    }

    @GetMapping("/fetchuserinfo/{cricheroName}")
    public Player fetchDataFromURL(@PathVariable String cricheroName) {
        return playerScaperService.getCricHeroInfo(cricheroName);
    }



	@GetMapping("/callApi/{search}")
	public String callApi(@PathVariable String search) throws IOException, InterruptedException {
		String url = API_ENDPOINT + URLEncoder.encode(search, StandardCharsets.UTF_8) + "?pagesize=12";
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.header("Api-Key", "cr!CkH3r0s").header("Device-Type", "Chrome: 119.0.0.0")
				.header("Udid", "ab7905ae4e2ddf11cc91ca0e05241cfc")

				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		return response.body();
	}

}
