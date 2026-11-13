package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "teller")
public class Teller extends Employee {

    @Column(name = "counter_number", nullable = false)
    private Integer counterNumber;

    public Teller() {
    }

    public Teller(String firstName,
                  String lastName,
                  BigDecimal salary,
                  Integer counterNumber) {

        super(firstName, lastName, salary);
        this.counterNumber = counterNumber;
    }

    public Integer getCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(Integer counterNumber) {
        this.counterNumber = counterNumber;
    }
}
