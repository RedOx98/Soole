package com.ecobank.soole.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.payload.auth.AccountDTO;
import com.ecobank.soole.payload.auth.AccountVerifiedViewDTO;
import com.ecobank.soole.payload.auth.AccountViewDTO;
import com.ecobank.soole.payload.auth.AuthoritiesDTO;
import com.ecobank.soole.payload.auth.PasswordDTO;
import com.ecobank.soole.payload.auth.ProfileDTO;
import com.ecobank.soole.payload.auth.TokenViewDTO;
import com.ecobank.soole.payload.auth.UserLoginDTO;
import com.ecobank.soole.payload.auth.VerifiedDTO;
import com.ecobank.soole.services.AccountService;
import com.ecobank.soole.services.EmailService;
import com.ecobank.soole.services.TokenService;
import com.ecobank.soole.util.constants.AccountError;
import com.ecobank.soole.util.constants.AccountSuccess;
import com.ecobank.soole.util.email.EmailDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Controller for Account management")
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private final AccountService accountService;

    @Autowired
    private EmailService emailService;

    @Value("${site.domain}")
    private String siteDomain;

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login to get token")
    // @PreAuthorize()
    public ResponseEntity<TokenViewDTO> token(@Valid @RequestBody UserLoginDTO userLogin)
            throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

            Optional<Account> optionalAccount = accountService.findByEmail(userLogin.getEmail());
            Account account = optionalAccount.get();
            // TokenDTO token = new TokenDTO(tokenService.generateToken(authentication));
            // result.put(token.toString(), account);
            String welcomeMessage = "You're welcome to Kiti, access the site on " + siteDomain + "swagger-ui/index.html";
            EmailDetails details = new EmailDetails(account.getEmail(), welcomeMessage, "Welcome to Kiti");
            if (emailService.sendMail(details) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            };
            return ResponseEntity
                    .ok(new TokenViewDTO(tokenService.generateToken(authentication), account.getAuthorities(),
                            account.getLevel(), account.getFirstName(), account.getLastName(), account.getUsername()));
        } catch (Exception e) {
            log.debug(AccountError.TOKEN_GENERATION_ERROR.toString() + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping(value = "/users/add", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "400", description = "Please enter a valid email and Password length between 6 to 20 characters")
    @ApiResponse(responseCode = "201", description = "Account added")
    @ApiResponse(responseCode = "200", description = "success")
    @Operation(summary = "Add a new user")
    public ResponseEntity<String> addUser(@Valid @RequestBody AccountDTO accountDTO) throws AuthenticationException {
        try {
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword_hash(accountDTO.getPassword_hash());
            account.setLevel(accountDTO.getLevel());
            account.setTelephone(accountDTO.getTelephone());
            account.setFirstName(accountDTO.getFirstName());
            account.setLastName(accountDTO.getLastName());
            account.setUsername(accountDTO.getUsername());
            accountService.save(account);

            return ResponseEntity.ok(AccountSuccess.ACCOUNT_ADDED.toString());
        } catch (Exception e) {
            return ResponseEntity.ok(AccountError.ADD_ACCOUNT_ERROR.toString() + ": " + e.getMessage());
        }
    }

    @GetMapping("/users")
    @ApiResponse(responseCode = "200", description = "List of users")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "List user API")
    @SecurityRequirement(name = "soole-demo-api")
    public List<AccountViewDTO> users() {

        List<AccountViewDTO> accounts = new ArrayList<>();

        for (Account users : accountService.findAll()) {
            accounts.add(new AccountViewDTO(users.getId(), users.getEmail(), users.getAuthorities(),
                    users.getCreatedAt(), users.getLevel(), users.getTelephone()));
        }
        return accounts;
    }

    // @GetMapping("/users")
    // @ApiResponse(responseCode = "200", description = "List of users")
    // @ApiResponse(responseCode = "401", description = "Token missing")
    // @ApiResponse(responseCode = "403", description = "Token error")
    // @Operation(summary = "List user API")
    // @SecurityRequirement(name = "soole-demo-api")
    // public List<AccountViewDTO> usersPage() {

    // List<AccountViewDTO> accounts = new ArrayList<>();

    // for (Account users: accountService.findAll()){
    // accounts.add(new
    // AccountViewDTO(users.getId(),users.getEmail(),users.getAuthorities(),
    // users.getCreatedAt(),users.getLevel(),users.getTelephone()));
    // }
    // return accounts;
    // }

    @GetMapping("/userspaginate")
    @ApiResponse(responseCode = "200", description = "List of users pagination")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "List users in paginated format")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<List<AccountViewDTO>> allUsers(
            @RequestParam(required = false, name = "sort_by", defaultValue = "createdAt") String sort_by,
            @RequestParam(required = false, name = "per_page", defaultValue = "2") String per_page,
            @RequestParam(required = false, name = "page", defaultValue = "page") String page) {
        Page<Account> accountsOnPage = accountService.findAccounts(Integer.parseInt(page) - 1,
                Integer.parseInt(per_page), sort_by);
        List<Account> accountList = accountsOnPage.getContent();
        int totalPages = accountsOnPage.getTotalPages();
        List<Integer> pages = new ArrayList<>();
        if (totalPages > 0) {
            pages = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
        }

        if (pages != null) {
            for (int link : pages) {
                // String active = "";
                if (link == accountsOnPage.getNumber()) {
                    // active = "active";
                }
                // Convert Account to AccountViewDTO
                List<AccountViewDTO> accounts = accountList.stream().map(account -> new AccountViewDTO(
                        account.getId(),
                        account.getEmail(),
                        account.getAuthorities(),
                        account.getCreatedAt(),
                        account.getLevel(),
                        account.getTelephone())).collect(Collectors.toList());
                System.out.println(accounts);
                return ResponseEntity.ok(accounts);
            }
        }
        System.out.println(accountsOnPage);
        return null;
    }

    @GetMapping(value = "/profile", produces = "application/json")
    @ApiResponse(responseCode = "200", description = "List of users")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "List user API")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<ProfileDTO> profile(Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            ProfileDTO profileDTO = new ProfileDTO(account.getId(), account.getEmail(), account.getAuthorities(),
                    account.getLevel(), account.getTelephone());
            return ResponseEntity.ok(profileDTO);
        }
        return new ResponseEntity<ProfileDTO>(new ProfileDTO(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/profile/update-password", produces = "application/json", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "List of users")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "Update profile")
    @SecurityRequirement(name = "soole-demo-api")
    public ProfileDTO updatePassword(@Valid @RequestBody PasswordDTO passwordDTO, Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        Account account = optionalAccount.get();
        account.setPassword_hash(passwordDTO.getPassword());
        accountService.save(account);
        ProfileDTO profileDTO = new ProfileDTO(account.getId(), account.getEmail(), account.getAuthorities(),
                account.getLevel(), account.getTelephone());

        return profileDTO;
    }

    @PutMapping(value = "/users/{userId}/update-authorities", produces = "application/json", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Updated authorities")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @ApiResponse(responseCode = "400", description = "Invalid user")
    @Operation(summary = "Update authorities")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<AccountViewDTO> updateAuthorities(@Valid @RequestBody AuthoritiesDTO authoritiesDTO,
            @PathVariable Long userId) {
        Optional<Account> optionalAccount = accountService.findById(userId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAuthorities(authoritiesDTO.getAuthorities());
            accountService.save(account);
            AccountViewDTO accountViewDTO = new AccountViewDTO(account.getId(), account.getEmail(),
                    account.getAuthorities(), account.getCreatedAt(), account.getLevel(), account.getTelephone());

            return ResponseEntity.ok(accountViewDTO);
        }
        return new ResponseEntity<>(new AccountViewDTO(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/users/{userId}/update-verified", produces = "application/json", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Updated verified")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @ApiResponse(responseCode = "400", description = "Invalid user")
    @Operation(summary = "Update verified")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<AccountVerifiedViewDTO> updateVerified(@Valid @RequestBody VerifiedDTO verifiedDTO,
            @PathVariable Long userId) {
        Optional<Account> optionalAccount = accountService.findById(userId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setVerified(verifiedDTO.getVerified());
            accountService.save(account);
            AccountVerifiedViewDTO accountVerifiedViewDTO = new AccountVerifiedViewDTO(account.getId(),
                    account.getEmail(), account.getAuthorities(), account.getCreatedAt(), account.getLevel(),
                    account.getTelephone(), account.getVerified());

            return ResponseEntity.ok(accountVerifiedViewDTO);
        }
        return new ResponseEntity<>(new AccountVerifiedViewDTO(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/profile/delete")
    @ApiResponse(responseCode = "200", description = "List of users")
    @ApiResponse(responseCode = "401", description = "Token missing")
    @ApiResponse(responseCode = "403", description = "Token error")
    @Operation(summary = "Delete profile")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String email = authentication.getName();
        Optional<Account> optionalAccount = accountService.findByEmail(email);
        if (optionalAccount.isPresent()) {
            accountService.deleteById(optionalAccount.get().getId());
            return ResponseEntity.ok("User deleted!");
        }
        return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
    }

    // @GetMapping("/test")
    // @Operation(summary = "welcome to soole")
    // // @SecurityRequirement(name = "soole-demo-api")
    // public String home() {
    // return "welcome home";
    // }
}
