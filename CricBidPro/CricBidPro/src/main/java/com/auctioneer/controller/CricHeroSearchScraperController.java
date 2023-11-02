package com.auctioneer.controller;

import com.auctioneer.service.CricHeroSearchScraper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/scrapper")
public class CricHeroSearchScraperController {

    private final CricHeroSearchScraper cricHeroSearchScraper;

    public CricHeroSearchScraperController(CricHeroSearchScraper cricHeroSearchScraper) {
        this.cricHeroSearchScraper = cricHeroSearchScraper;
    }

    @GetMapping("/fetch-data")
    public ResponseEntity<Map<String, Object>> fetchDataFromURL() {
        return cricHeroSearchScraper.getMainpageResponse();
    }

}
