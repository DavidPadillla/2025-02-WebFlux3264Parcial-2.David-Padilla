package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;

public class AccountOwnerBalanceDTO {

    private String ownerName;
    private BigDecimal balance;

    public AccountOwnerBalanceDTO() {}

    public AccountOwnerBalanceDTO(String ownerName, BigDecimal balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
