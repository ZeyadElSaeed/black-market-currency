package com.zeyad.blackmarketcurrency.controllers;

import com.zeyad.blackmarketcurrency.models.Website;
import com.zeyad.blackmarketcurrency.services.WebsiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/website")
public class WebsiteController {

    private final WebsiteService websiteService;

    @GetMapping("/add")
    public String addWebsiteForm(Model model){
        model.addAttribute("website", new Website());
        return "addWebsite";
    }

    @PostMapping("/add")
    public String addWebsite(@Valid @ModelAttribute("website") Website website, BindingResult result){
        if(result.hasErrors()){
            return "addWebsite";
        }
        websiteService.addWebsite(website);
        return "success";
    }

    @GetMapping("/all")
    public String getAllWebsites(Model model){
        model.addAttribute("websites", websiteService.getAllWebsites());
        return "allWebsites";
    }

    @GetMapping("/delete")
    public String deleteAllWebsite(){
        websiteService.deleteAllWebsite();
        return "allWebsites";
    }


}
