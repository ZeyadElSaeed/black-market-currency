package com.zeyad.blackmarketcurrency.controllers;

import com.zeyad.blackmarketcurrency.models.Client;
import com.zeyad.blackmarketcurrency.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/add")
    public String registerForm(Model model){
        model.addAttribute("client", new Client());
        return "addClient";
    }

    @PostMapping("/add")
    public String register(@Valid @ModelAttribute("client") Client client, BindingResult result){
        if(result.hasErrors()){
            return "addClient";
        }
        clientService.register(client);
        return "success";
    }

    @GetMapping("/all")
    public String getAllClients(Model model){
        model.addAttribute("clients", clientService.getAllClients());
        return "allClients";
    }

    @GetMapping("/delete")
    public String deleteAllClients(){
        clientService.deleteAllClients();
        return "allClients";
    }


}
