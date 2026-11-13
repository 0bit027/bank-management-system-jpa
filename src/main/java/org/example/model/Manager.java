package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manager")
public class Manager extends Employee {

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal bonus;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<LoanApproval> loanApprovals = new ArrayList<>();

    public Manager() {
    }

    public Manager(String firstName,
                   String lastName,
                   BigDecimal salary,
                   BigDecimal bonus) {

        super(firstName, lastName, salary);
        this.bonus = bonus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public List<LoanApproval> getLoanApprovals() {
        return loanApprovals;
    }
}
