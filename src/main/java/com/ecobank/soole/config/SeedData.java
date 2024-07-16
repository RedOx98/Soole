package com.ecobank.soole.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.models.Bus;
import com.ecobank.soole.services.AccountService;
import com.ecobank.soole.services.BusService;
import com.ecobank.soole.util.constants.Authority;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BusService busService;

    @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();
        Account account05 = new Account();

        Bus bus01= new Bus();
        Bus bus02= new Bus();
        Bus bus03= new Bus();
        Bus bus04= new Bus();
        Bus bus05= new Bus();

        List<Account> accounts01 = new ArrayList<>();
        accounts01.add(account01);

        List<Account> accounts02 = new ArrayList<>();
        accounts02.add(account02);

        List<Account> accounts03 = new ArrayList<>();
        accounts03.add(account03);

        List<Account> accounts04 = new ArrayList<>();
        accounts04.add(account04);

        List<Account> accounts05 = new ArrayList<>();
        accounts05.add(account05);

        bus01.setCaptain("Johnson Bamidele");
        bus01.setDriver("Micheal Micholo");
        bus01.setPlateNumber("WX45637H");
        bus01.setRep("Olaide Hammed");
        bus01.setRoute("IKORODU");
        bus01.setAccounts(accounts01);
        busService.save(bus01);

        bus02.setCaptain("Olamide Kareem");
        bus02.setDriver("Yemi Ola");
        bus02.setPlateNumber("WBV5637H");
        bus02.setRep("Charles Shelu");
        bus02.setRoute("KETU");
        bus02.setAccounts(accounts02);
        busService.save(bus02);

        bus03.setCaptain("Wale BOms");
        bus03.setDriver("Wole Adeyele");
        bus03.setPlateNumber("TV45637H");
        bus03.setRep("Winnie Solks");
        bus03.setRoute("BERGER");
        bus03.setAccounts(accounts03);
        busService.save(bus03);

        bus04.setCaptain("Biola Tosin");
        bus04.setDriver("Sola Biodun");
        bus04.setPlateNumber("WX45637H");
        bus04.setRep("Seyi Sayo");
        bus04.setRoute("SANGO");
        bus04.setAccounts(accounts04);
        busService.save(bus04);

        bus05.setCaptain("Biodun Ajijola");
        bus05.setDriver("Vancouver Busman");
        bus05.setPlateNumber("AR85637H");
        bus05.setRep("Sumaya Mariam");
        bus05.setRoute("OJOTA");
        bus05.setAccounts(accounts05);
        busService.save(bus05);

        account01.setEmail("olaskeet@gmail.com");
        account01.setPassword_hash("password");
        account01.setLevel("Executive Assistant");
        account01.setTelephone("09036123576");
        account01.setFirstName("olaide");
        account01.setLastName("hammed");
        account01.setUsername("olahammed");
        account01.setRoute("IKORODU");
        account01.setDepartment("Technology");
        account01.setAffiliate("ENG");
        account01.setStaff_id("10928");
        account01.setStatus("activated");
        account01.setVerified("approved");
        accountService.mapToBus(account01, bus01.getId());

        account02.setEmail("olahammed@ecobank.com");
        account02.setPassword_hash("password");
        account02.setLevel("Executive Assistant");
        account02.setTelephone("09036123576");
        account02.setFirstName("olaide");
        account02.setLastName("hammed");
        account02.setUsername("olahammed");
        account02.setAuthorities(Authority.ADMIN.toString());
        account02.setRoute("KETU");
        account02.setDepartment("Technology");
        account02.setAffiliate("ENG");
        account02.setStaff_id("10928");
        account02.setStatus("activated");
        account02.setVerified("pending");
        accountService.mapToBus(account02, bus02.getId());

        account03.setEmail("tvincent@ecobank.com");
        account03.setPassword_hash("password");
        account03.setLevel("Executive Trainee");
        account03.setTelephone("09036123576");
        account03.setFirstName("Tosin");
        account03.setLastName("Vincent");
        account03.setUsername("Tosmic");
        account03.setAuthorities(Authority.CAPTAIN.toString());
        account03.setRoute("OJOTA");
        account03.setDepartment("Technology");
        account03.setAffiliate("ENG");
        account03.setStaff_id("11915");
        account03.setStatus("pending");
        account03.setVerified("approved");
        accountService.mapToBus(account03, bus03.getId());

        account04.setEmail("emmanuel@ecobank.com");
        account04.setPassword_hash("password");
        account04.setLevel("Executive Trainee");
        account04.setTelephone("09036123576");
        account04.setFirstName("Emmanuel");
        account04.setLastName("Onyejeme");
        account04.setUsername("Onyeemmanuel");
        account04.setAuthorities(Authority.SUPERUSER.toString());
        account04.setRoute("BERGER");
        account04.setDepartment("Technology");
        account04.setAffiliate("ENG");
        account04.setStaff_id("12928");
        account04.setStatus("activated");
        account04.setVerified("pending");
        accountService.mapToBus(account04, bus01.getId());

        account05.setEmail("micheal@ecobank.com");
        account05.setPassword_hash("password");
        account05.setLevel("Contract");
        account05.setTelephone("09036123576");
        account05.setFirstName("Micheal");
        account05.setLastName("Miicheal");
        account05.setUsername("micheali");
        account05.setAuthorities(Authority.DRIVER.toString());
        account05.setRoute("SANGO");
        account05.setDepartment("Technology");
        account05.setAffiliate("ENG");
        account05.setStaff_id("13928");
        account05.setStatus("deactivated");
        account05.setVerified("rejected");
        accountService.mapToBus(account05, bus05.getId());

    }
}
