package com.ecobank.soole.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.models.Bus;
import com.ecobank.soole.models.BusStop;
import com.ecobank.soole.payload.bus.CreateBusDTO;
import com.ecobank.soole.payload.bus.CreateBusStopDTO;
import com.ecobank.soole.services.AccountService;
import com.ecobank.soole.services.BusService;
import com.ecobank.soole.services.BusStopService;
import com.ecobank.soole.util.constants.Authority;
import com.ecobank.soole.util.constants.BusEnum;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BusService busService;

    @Autowired
    private BusStopService busStopService;

    @Override
    public void run(String... args) throws Exception {
        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();
        Account account05 = new Account();
        Account account06 = new Account();
        Account account07 = new Account();
        Account account08 = new Account();
        Account account09 = new Account();
        Account account10 = new Account();
        Account account11 = new Account();
        Account account12 = new Account();

        // Bus bus01 = new Bus();
        // Bus bus02 = new Bus();
        // Bus bus03 = new Bus();
        // Bus bus04 = new Bus();
        // Bus bus05 = new Bus();

        // List<Account> accounts = new ArrayList<>();
        // accounts.add(account01);
        // accounts.add(account02);
        // accounts.add(account03);
        // accounts.add(account04);
        // accounts.add(account05);

        // Set<BusStop> busStop = new HashSet<>();

        // bus01.setBusId(1l);
        // bus01.setBusModel("KTU1012");
        // bus01.setAccounts(accounts);
        // bus01.setDriverId("5");
        // bus01.setBusCapacity(30);
        // bus01.setBusColor("White");
        // bus01.setBusStops(busStop);
        // bus01.setBusNumber("001");
        // bus01.setOperationalStatus(BusEnum.OperationalStatus.ACTIVE);
        // CreateBusDTO busDTO01 = new CreateBusDTO(bus01.getBusNumber(), bus01.getOperationalStatus(),
        //         bus01.getBusModel(), bus01.getBusCapacity(), bus01.getBusColor());

        // busService.createBus(busDTO01);
        // bus02.setBusId(2l);
        // bus02.setBusModel("BRG1012");
        // bus02.setAccounts(accounts);
        // bus02.setDriverId("2");
        // bus02.setBusCapacity(29);
        // bus02.setBusColor("BROWN");
        // bus02.setBusStops(busStop);
        // bus02.setBusNumber("003");
        // bus02.setOperationalStatus(BusEnum.OperationalStatus.ACTIVE);
        // CreateBusDTO busDTO02 = new CreateBusDTO(bus02.getBusNumber(), bus02.getOperationalStatus(),
        //         bus02.getBusModel(), bus02.getBusCapacity(), bus02.getBusColor());
        // busService.createBus(busDTO02);

        // BusStop bstp01 = new BusStop();
        // bstp01.setBus(bus01);
        // bstp01.setBusStopId(1l);
        // bstp01.setBusStopName("Ogolonto");
        // bstp01.setCreatedAt(LocalDateTime.now());
        // busStopService.createBusStop(new CreateBusStopDTO(), bstp01.getBusStopId().toString(0));

        // BusStop bstp02 = new BusStop();
        // bstp02.setBus(bus01);
        // bstp02.setBusStopId(1l);
        // bstp02.setBusStopName("IKORODU");
        // bstp02.setCreatedAt(LocalDateTime.now());
        // busStopService.createBusStop(new CreateBusStopDTO(), bstp02.getBusStopId().toString(0));

        // busStop.add(bstp01);
        // busStop.add(bstp02);

        account01.setEmail("user@gmail.com");
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
        account01.setVerified("APPROVED");
        accountService.save(account01);
        // accountService.mapToBus(account01, bus01.getBusId());

        account02.setEmail("admin@gmail.com");
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
        account02.setVerified("APPROVED");
        account02.setSpecial("SPECIAL");
        accountService.save(account02);
        // accountService.mapToBus(account02, bus02.getBusId());

        account03.setEmail("captain@ecobank.com");
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
        account03.setVerified("APPROVED");
        accountService.save(account03);
        // accountService.mapToBus(account03, bus03.getBusId());

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
        accountService.save(account04);
        // accountService.mapToBus(account04, bus01.getBusId());

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
        accountService.save(account05);
        // accountService.mapToBus(account05, bus05.getBusId());

        account06.setEmail("olaomoriwo@ecobank.com");
        account06.setPassword_hash("password");
        account06.setLevel("SBO");
        account06.setTelephone("09036123576");
        account06.setFirstName("Omoriwo");
        account06.setLastName("Vincent");
        account06.setUsername("oomoriwo");
        account06.setAuthorities(Authority.ADMIN.toString());
        account06.setRoute("BERGER");
        account06.setDepartment("Corporate Banking");
        account06.setAffiliate("ENG");
        account06.setStaff_id("12328");
        account06.setStatus("activated");
        account06.setVerified("accepted");
        accountService.save(account06);
        // accountService.mapToBus(account05, bus05.getBusId());

        account07.setEmail("user1@ecobank.com");
        account07.setPassword_hash("password");
        account07.setLevel("Executive Assistant");
        account07.setTelephone("09036123576");
        account07.setFirstName("john");
        account07.setLastName("doe");
        account07.setUsername("johndoe");
        account07.setRoute("BERGER");
        account07.setDepartment("Technology");
        account07.setAffiliate("ENG");
        account07.setStaff_id("10929");
        account07.setStatus("activated");
        account07.setVerified("APPROVED");
        accountService.save(account07);

        account08.setEmail("user2@ecobank.com");
        account08.setPassword_hash("password");
        account08.setLevel("Executive Assistant");
        account08.setTelephone("09036123576");
        account08.setFirstName("james");
        account08.setLastName("daniel");
        account08.setUsername("danieljames");
        account08.setRoute("AJAH-LAKOWE");
        account08.setDepartment("Technology");
        account08.setAffiliate("ENG");
        account08.setStaff_id("10930");
        account08.setStatus("activated");
        account08.setVerified("APPROVED");
        accountService.save(account08);

        account09.setEmail("user3@ecobank.com");
        account09.setPassword_hash("password");
        account09.setLevel("Executive Assistant");
        account09.setTelephone("09036123576");
        account09.setFirstName("graham");
        account09.setLastName("aubrey");
        account09.setUsername("aubreygraham");
        account09.setRoute("IKORODU");
        account09.setDepartment("Technology");
        account09.setAffiliate("ENG");
        account09.setStaff_id("10931");
        account09.setStatus("activated");
        account09.setVerified("APPROVED");
        accountService.save(account09);

        account10.setEmail("user4@ecobank.com");
        account10.setPassword_hash("password");
        account10.setLevel("Executive Assistant");
        account10.setTelephone("09036123576");
        account10.setFirstName("rose");
        account10.setLastName("silva");
        account10.setUsername("rosesilva");
        account10.setRoute("OSHODI");
        account10.setDepartment("Technology");
        account10.setAffiliate("ENG");
        account10.setStaff_id("10932");
        account10.setStatus("activated");
        account10.setVerified("APPROVED");
        accountService.save(account10);

        account11.setEmail("user5@ecobank.com");
        account11.setPassword_hash("password");
        account11.setLevel("Executive Assistant");
        account11.setTelephone("09036123576");
        account11.setFirstName("esther");
        account11.setLastName("james");
        account11.setUsername("estherjames");
        account11.setRoute("IYANA-IPAJA");
        account11.setDepartment("Technology");
        account11.setAffiliate("ENG");
        account11.setStaff_id("10933");
        account11.setStatus("activated");
        account11.setVerified("APPROVED");
        accountService.save(account11);

        account12.setEmail("user6@ecobank.com");
        account12.setPassword_hash("password");
        account12.setLevel("Executive Assistant");
        account12.setTelephone("09036123576");
        account12.setFirstName("benjamin");
        account12.setLastName("button");
        account12.setUsername("benjaminbutton");
        account12.setRoute("AJAH-LAKOWE");
        account12.setDepartment("Technology");
        account12.setAffiliate("ENG");
        account12.setStaff_id("10934");
        account12.setStatus("activated");
        account12.setVerified("APPROVED");
        accountService.save(account12);

    }
}
