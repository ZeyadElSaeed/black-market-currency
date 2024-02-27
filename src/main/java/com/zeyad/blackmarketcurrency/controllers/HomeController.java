package com.zeyad.blackmarketcurrency.controllers;

import com.zeyad.blackmarketcurrency.services.WebScrapingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final WebScrapingService service;

    @GetMapping()
    public String homePage(Model model){
        String message = "Current Price: " + service.getCurrentPrice() + " EGP";
        model.addAttribute("priceMessage", message);
        return "home";
    }
}
