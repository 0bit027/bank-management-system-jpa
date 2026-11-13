package org.example.init;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.enums.*;
import org.example.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataInitializer {

    public static void insertSampleData(EntityManager em) {

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Bank bank = new Bank(
                    "UniCredit Bank",
                    "Belgrade, Serbia"
            );

            em.persist(bank);

            Client client1 = new Client(
                    "Nikola",
                    "Jovanovic",
                    "nikola@gmail.com",
                    LocalDate.of(2003, 5, 15)
            );

            Client client2 = new Client(
                    "Marko",
                    "Petrovic",
                    "marko@gmail.com",
                    LocalDate.of(1998, 8, 20)
            );

            Client client3 = new Client(
                    "Ana",
                    "Ilic",
                    "ana@gmail.com",
                    LocalDate.of(1995, 3, 10)
            );

            client1.setBank(bank);
            client2.setBank(bank);
            client3.setBank(bank);

            bank.getClients().add(client1);
            bank.getClients().add(client2);
            bank.getClients().add(client3);

            em.persist(client1);
            em.persist(client2);
            em.persist(client3);

            Manager manager1 = new Manager(
                    "Petar",
                    "Petrovic",
                    new BigDecimal("120000"),
                    new BigDecimal("30000")
            );

            Manager manager2 = new Manager(
                    "Milan",
                    "Nikolic",
                    new BigDecimal("110000"),
                    new BigDecimal("25000")
            );

            manager1.setBank(bank);
            manager2.setBank(bank);

            bank.getEmployees().add(manager1);
            bank.getEmployees().add(manager2);

            em.persist(manager1);
            em.persist(manager2);

            Teller teller1 = new Teller(
                    "Jelena",
                    "Jovic",
                    new BigDecimal("80000"),
                    1
            );

            Teller teller2 = new Teller(
                    "Ivana",
                    "Markovic",
                    new BigDecimal("85000"),
                    2
            );

            teller1.setBank(bank);
            teller2.setBank(bank);

            bank.getEmployees().add(teller1);
            bank.getEmployees().add(teller2);

            em.persist(teller1);
            em.persist(teller2);

            Account account1 = new Account(
                    "RS1001",
                    new BigDecimal("150000"),
                    AccountType.SAVINGS,
                    LocalDate.now()
            );

            Account account2 = new Account(
                    "RS1002",
                    new BigDecimal("85000"),
                    AccountType.CURRENT,
                    LocalDate.now()
            );

            Account account3 = new Account(
                    "RS1003",
                    new BigDecimal("220000"),
                    AccountType.BUSINESS,
                    LocalDate.now()
            );

            Account account4 = new Account(
                    "RS1004",
                    new BigDecimal("50000"),
                    AccountType.SAVINGS,
                    LocalDate.now()
            );

            Account account5 = new Account(
                    "RS1005",
                    new BigDecimal("120000"),
                    AccountType.CURRENT,
                    LocalDate.now()
            );

            account1.setClient(client1);
            account2.setClient(client1);

            account3.setClient(client2);

            account4.setClient(client3);
            account5.setClient(client3);

            client1.getAccounts().add(account1);
            client1.getAccounts().add(account2);

            client2.getAccounts().add(account3);

            client3.getAccounts().add(account4);
            client3.getAccounts().add(account5);

            em.persist(account1);
            em.persist(account2);
            em.persist(account3);
            em.persist(account4);
            em.persist(account5);

            Card card1 = new Card(
                    "1111222233334444",
                    LocalDate.of(2029,12,31),
                    CardType.DEBIT
            );

            Card card2 = new Card(
                    "5555666677778888",
                    LocalDate.of(2028,6,30),
                    CardType.CREDIT
            );

            Card card3 = new Card(
                    "9999000011112222",
                    LocalDate.of(2030,3,31),
                    CardType.DEBIT
            );

            card1.setClient(client1);
            card2.setClient(client2);
            card3.setClient(client3);

            client1.getCards().add(card1);
            client2.getCards().add(card2);
            client3.getCards().add(card3);

            em.persist(card1);
            em.persist(card2);
            em.persist(card3);

            Loan loan1 = new Loan(
                    new BigDecimal("500000"),
                    new BigDecimal("5.5"),
                    LoanStatus.ACTIVE
            );

            Loan loan2 = new Loan(
                    new BigDecimal("300000"),
                    new BigDecimal("6.2"),
                    LoanStatus.ACTIVE
            );

            Loan loan3 = new Loan(
                    new BigDecimal("800000"),
                    new BigDecimal("4.9"),
                    LoanStatus.PAID_OFF
            );

            loan1.setClient(client1);
            loan2.setClient(client2);
            loan3.setClient(client3);

            client1.getLoans().add(loan1);
            client2.getLoans().add(loan2);
            client3.getLoans().add(loan3);

            em.persist(loan1);
            em.persist(loan2);
            em.persist(loan3);

            LoanApproval approval1 = new LoanApproval(
                    LocalDate.now(),
                    ApprovalStatus.APPROVED,
                    "Approved"
            );

            LoanApproval approval2 = new LoanApproval(
                    LocalDate.now(),
                    ApprovalStatus.PENDING,
                    "Waiting"
            );

            LoanApproval approval3 = new LoanApproval(
                    LocalDate.now(),
                    ApprovalStatus.APPROVED,
                    "Approved with low risk"
            );

            approval1.setManager(manager1);
            approval1.setLoan(loan1);

            approval2.setManager(manager2);
            approval2.setLoan(loan2);

            approval3.setManager(manager1);
            approval3.setLoan(loan3);

            em.persist(approval1);
            em.persist(approval2);
            em.persist(approval3);

            Transaction t1 = new Transaction(
                    new BigDecimal("5000"),
                    LocalDate.now(),
                    TransactionType.DEPOSIT,
                    "Salary payment"
            );

            Transaction t2 = new Transaction(
                    new BigDecimal("2000"),
                    LocalDate.now(),
                    TransactionType.WITHDRAWAL,
                    "ATM withdrawal"
            );

            Transaction t3 = new Transaction(
                    new BigDecimal("10000"),
                    LocalDate.now(),
                    TransactionType.TRANSFER,
                    "Transfer to savings"
            );

            Transaction t4 = new Transaction(
                    new BigDecimal("1500"),
                    LocalDate.now(),
                    TransactionType.DEPOSIT,
                    "Cash deposit"
            );

            Transaction t5 = new Transaction(
                    new BigDecimal("3000"),
                    LocalDate.now(),
                    TransactionType.WITHDRAWAL,
                    "Shopping"
            );

            Transaction t6 = new Transaction(
                    new BigDecimal("7000"),
                    LocalDate.now(),
                    TransactionType.TRANSFER,
                    "Monthly transfer"
            );

            Transaction t7 = new Transaction(
                    new BigDecimal("4000"),
                    LocalDate.now(),
                    TransactionType.DEPOSIT,
                    "Bonus payment"
            );

            Transaction t8 = new Transaction(
                    new BigDecimal("2500"),
                    LocalDate.now(),
                    TransactionType.WITHDRAWAL,
                    "Bills payment"
            );

            t1.setAccount(account1);
            t2.setAccount(account1);

            t3.setAccount(account2);

            t4.setAccount(account3);
            t5.setAccount(account3);

            t6.setAccount(account4);

            t7.setAccount(account5);
            t8.setAccount(account5);

            account1.getTransactions().add(t1);
            account1.getTransactions().add(t2);

            account2.getTransactions().add(t3);

            account3.getTransactions().add(t4);
            account3.getTransactions().add(t5);

            account4.getTransactions().add(t6);

            account5.getTransactions().add(t7);
            account5.getTransactions().add(t8);

            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.persist(t4);
            em.persist(t5);
            em.persist(t6);
            em.persist(t7);
            em.persist(t8);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        }
    }
}
