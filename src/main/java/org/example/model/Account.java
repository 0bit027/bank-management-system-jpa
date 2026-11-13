package org.example.model;

import jakarta.persistence.*;
import org.example.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({

        @NamedQuery(
                name = "Account.findAll",
                query = "SELECT a FROM Account a"
        ),

        @NamedQuery(
                name = "Account.balanceGreaterThan",
                query = "SELECT a FROM Account a WHERE a.balance > :balance"
        )

})
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(String accountNumber,
                   BigDecimal balance,
                   AccountType accountType,
                   LocalDate openingDate) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.openingDate = openingDate;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
