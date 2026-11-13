package org.example.model;

import jakarta.persistence.*;
import org.example.enums.LoanStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status", nullable = false)
    private LoanStatus loanStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanApproval> loanApprovals = new ArrayList<>();

    public Loan() {
    }

    public Loan(BigDecimal amount,
                BigDecimal interestRate,
                LoanStatus loanStatus) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.loanStatus = loanStatus;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LoanApproval> getLoanApprovals() {
        return loanApprovals;
    }
}