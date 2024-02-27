package com.zeyad.blackmarketcurrency.services;

import com.zeyad.blackmarketcurrency.models.Website;
import com.zeyad.blackmarketcurrency.repositories.WebsiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebsiteService {
    private final WebsiteRepository websiteRepository;

    public Website addWebsite(Website website){
        return websiteRepository.save(website);
    }
    public List<Website> getAllWebsites(){
        return websiteRepository.findAll();
    }

    public void deleteAllWebsite(){
        websiteRepository.deleteAll();
    }
}
