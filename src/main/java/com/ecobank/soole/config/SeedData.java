package com.ecobank.soole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.services.AccountService;
import com.ecobank.soole.util.constants.Authority;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("olaskeet@gmail.com");
        account01.setPassword_hash("password");
        account01.setLevel("Executive Assistant");
        account01.setTelephone("09036123576");
        account01.setFirstName("olaide");
        account01.setLastName("hammed");
        account01.setUsername("olahammed");
        accountService.save(account01);

        account02.setEmail("olahammed@ecobank.com");
        account02.setPassword_hash("password");
        account02.setLevel("Executive Assistant");
        account02.setTelephone("09036123576");
        account02.setFirstName("olaide");
        account02.setLastName("hammed");
        account02.setUsername("olahammed");
        account02.setAuthorities(Authority.ADMIN.toString());
        accountService.save(account02);

        account03.setEmail("tvincent@ecobank.com");
        account03.setPassword_hash("password");
        account03.setLevel("Executive Trainee");
        account03.setTelephone("09036123576");
        account03.setFirstName("Tosin");
        account03.setLastName("Vincent");
        account03.setUsername("Tosmic");
        account03.setAuthorities(Authority.CAPTAIN.toString());
        accountService.save(account03);

        account04.setEmail("emmanuel@ecobank.com");
        account04.setPassword_hash("password");
        account04.setLevel("Executive Trainee");
        account04.setTelephone("09036123576");
        account04.setFirstName("Emmanuel");
        account04.setLastName("Onyejeme");
        account04.setUsername("Onyeemmanuel");
        account04.setAuthorities(Authority.SUPERUSER.toString());
        accountService.save(account04);
    }
}
