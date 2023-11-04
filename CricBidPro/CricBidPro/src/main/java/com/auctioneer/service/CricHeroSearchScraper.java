package com.auctioneer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CricHeroSearchScraper {

    private final WebClient webClient;

    @Autowired
    public CricHeroSearchScraper(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://cricheroes.in").build();
    }

    public Mono<String> getUserInfo(String cricheroName) {
        String searchEndpoint = "/api/v1/other/global-search/"+ URLEncoder.encode(cricheroName, StandardCharsets.UTF_8)+"?pagesize=12";

        return this.webClient.get()
                .uri(searchEndpoint)
                .headers(httpHeaders -> httpHeaders.addAll(buildCustomHeaders()))
                .retrieve()
                .bodyToMono(String.class);
    }

    private HttpHeaders buildCustomHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "en-US,en;q=0.5");
        headers.add("api-key", "cr!CkH3r0s");
        headers.add("Connection", "keep-alive");
        headers.add("Device-Type", "Firefox: 119.0");
        headers.add("Host", "cricheroes.in");
        headers.add("If-None-Match", "W/\"19e-5ZNG9WT/UmbdtjoW4WvDwg\"");
        headers.add("Sec-Fetch-Dest", "empty");
        headers.add("Sec-Fetch-Mode", "cors");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("TE", "trailers");
        headers.add("udid", "bcf417562c89e0bc96136dd10c325926");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/119.0");
        headers.add("X-Requested-With", "XMLHttpRequest");

        // Add cookies
        headers.add("Cookie", buildCookieHeaderValue());

        return headers;
    }

    // Method to build the Cookie header value with the provided cookies
    private String buildCookieHeaderValue() {

        return String.join("; ", List.of(
                "FCNEC=[[\"AKsRol_bHBq4WhRa87GAdJCLoshHwEl6ue8ZpiSGIPXm8kOZelGIlku8xpVfbGdZt2Gxz23bFcFnJU0wXAVfeXU4YgVVpaqjFpbq4l8LAeyvDeeOQPPDjNqHPy7xfeC82lVauSBgmbeoylGwI9-7lf0ffHM7DYUCFg==\"],null,[]]",
                "__gads=ID=8f03e9b63159bc25-22c0cd917a8000d1:T=1697974294:RT=1698859033:S=ALNI_MZLukZo_TtDlfLXQLJ5VsD4uP0nEA",
                "__gpi=UID=00000c6d3fd324b1:T=1697974294:RT=1698859033:S=ALNI_Mb51hJh0Ul_093st06FbcqRSe4-Ng",
                "__qca=P0-1642500270-1698289920245",
                "_bid=d4bb255d919e5ecca984ca460aeffa0d",
                "_clck=xbil5h|2|fgc|0|1399",
                "_clsk=1p9mxr6|1698859072199|3|1|o.clarity.ms/collect",
                "_ga=GA1.1.1554580682.1697974295",
                "_ga_2MNGBJB3NG=GS1.1.1698859045.5.1.1698859149.60.0.0",
                "_ga_RHRT76MSXD=GS1.1.1698859045.5.1.1698859149.60.0.0",
                "_gcl_au=1.1.1676337311.1697974295",
                "udid=bcf417562c89e0bc96136dd10c325926"
        ));
    }

}
