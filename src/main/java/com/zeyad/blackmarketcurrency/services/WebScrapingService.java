package com.zeyad.blackmarketcurrency.services;

import com.zeyad.blackmarketcurrency.models.Website;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebScrapingService {

    private final WebsiteService websiteService;
    private final ClientService clientService;
    private float currentPrice = 0;

    public float getCurrentPrice(){
        return currentPrice;
    }

    @Scheduled(fixedDelay = 1000 * 10)
    public void startScraping(){
        List<Website> websites = websiteService.getAllWebsites();
        float totalprices = 0;
        int count = 0;
        for(Website website: websites){
            float value = scrapeWebsite(website.getUrl(), website.getXPath());
            totalprices = (value == 0)? totalprices : totalprices + value;
            count = (value == 0)? count : count + 1;
        }
        if(count != 0){
            currentPrice = totalprices/count;
            clientService.alertClients(currentPrice);
        }
    }

    private float scrapeWebsite(String url, String xPath) {
        float value = 0;
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.selectXpath(xPath);
            for (Element element : elements) {
                String text = element.text();
                log.info("Extract " + text +" from: " + url);
                value += Float.valueOf(text);
            }

        } catch (Exception e) {
            log.error("URL has a problem: " + url);
            e.printStackTrace();
        }
        return value;
    }
}
