package com.parcial.dos.parcialdos.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.service.IAccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IAccountService service;

    public AccountController(IAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) {
        AccountResponseDTO response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        List<AccountResponseDTO> accounts = service.getAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable("id") Long id) {
        AccountResponseDTO account = service.getById(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody AccountRequestDTO request) {
        String result = service.update(id, request);
        if (result.equals("Account not found")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byNumber/{accountNumber}")
    public ResponseEntity<AccountOwnerBalanceDTO> getByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        AccountOwnerBalanceDTO dto = service.findByAccountNumber(accountNumber);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
