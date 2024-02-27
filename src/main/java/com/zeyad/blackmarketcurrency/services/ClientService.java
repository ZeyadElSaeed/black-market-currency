package com.zeyad.blackmarketcurrency.services;

import com.zeyad.blackmarketcurrency.models.Client;
import com.zeyad.blackmarketcurrency.repositories.ClientRepository;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;


    public Client register(Client request){
        if(!clientRepository.findAllByEmail(request.getEmail()).isEmpty()){
            log.error("Email Already Exist: " + request.getEmail());
            return null;
        }
        Client client = Client.builder()
                .email(request.getEmail())
                .lastPrice(0)
                .alert(true)
                .build();

        return clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public void deleteAllClients(){
        clientRepository.deleteAll();
    }

    public void deleteByEmail(String email){
        clientRepository.deleteByEmail(email);
    }

    public void alertClients(float currentPrice){
        List<Client> clients = getAllClients()
                .stream()
                .filter(c -> c.isAlert()
                        && Math.abs(c.getLastPrice() - currentPrice) >= 3 )
                .collect(Collectors.toList());

        for (Client client: clients){
            String to = client.getEmail();
            String subject = "Huge Change in USD Price";
            String body = "Dear Client,\n\n"
                    + "We hope you're having a great day!\n"
                    + "There was a big change in USD price by: " + Math.abs(client.getLastPrice() - currentPrice)
                    + "\nThe current USD price now is: " + currentPrice + " EGP\n"
                    + "Best regards,\n"
                    + "Black Currency Website";
            sendEmail(to, subject, body, client, currentPrice);
        }
    }

    @Async
    protected void sendEmail(String to, String subject, String body, Client client, float currentPrice) {
        try {
             MimeMessagePreparator preparatory = (mimeMessage) -> {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(new InternetAddress(emailFrom, "Black Currency Website"));
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(body, true);
            };
            mailSender.send(preparatory);
            client.setLastPrice(currentPrice);
            clientRepository.save(client);
        } catch (Exception e) {
            log.error("Failed to Send Email to: " + to);
            e.printStackTrace();
        }

    }


}
