package com.parcial.dos.parcialdos.account.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance() != null ? request.getBalance() : account.getBalance());

        accountRepository.save(account);
        return new AccountResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance()
        );
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(a -> new AccountResponseDTO(
                        a.getId(),
                        a.getAccountNumber(),
                        a.getOwnerName(),
                        a.getBalance()))
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        Account a = accountRepository.findById(id).orElse(null);
        if (a == null) return null;
        return new AccountResponseDTO(
                a.getId(),
                a.getAccountNumber(),
                a.getOwnerName(),
                a.getBalance()
        );
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        Account a = accountRepository.findById(id).orElse(null);
        if (a == null) return "Account not found";

        // Solo actualizar campos que no sean null
        if (request.getAccountNumber() != null) a.setAccountNumber(request.getAccountNumber());
        if (request.getOwnerName() != null) a.setOwnerName(request.getOwnerName());
        if (request.getBalance() != null) a.setBalance(request.getBalance());

        accountRepository.save(a);
        return "Account updated successfully";
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO findByAccountNumber(String accountNumber) {
        Account a = accountRepository.findByAccountNumber(accountNumber);
        if (a == null) return null;
        return new AccountOwnerBalanceDTO(a.getOwnerName(), a.getBalance());
    }
}
