package org.example.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Account;
import org.example.model.Client;

import java.math.BigDecimal;
import java.util.List;

public class QueryExamples {

    public static void simpleQuery(EntityManager em) {

        Query query = em.createQuery("SELECT c FROM Client c");

        List<Client> clients = query.getResultList();

        clients.forEach(c ->
                System.out.println(c.getFirstName() + " " + c.getLastName())
        );
    }

    public static void typedQuery(EntityManager em) {

        TypedQuery<Account> query =
                em.createQuery(
                        "SELECT a FROM Account a WHERE a.balance > :balance",
                        Account.class);

        query.setParameter("balance", new BigDecimal("100000"));

        List<Account> accounts = query.getResultList();

        accounts.forEach(a ->
                System.out.println(
                        a.getAccountNumber() + " " + a.getBalance()
                ));
    }

    public static void namedQuery(EntityManager em) {

        List<Client> clients = em
                .createNamedQuery("Client.findAll", Client.class)
                .getResultList();

        clients.forEach(c ->
                System.out.println(c.getFirstName()));
    }

    public static void multipleNamedQueries(EntityManager em) {

        List<Account> accounts =
                em.createNamedQuery(
                                "Account.balanceGreaterThan",
                                Account.class)
                        .setParameter("balance", new BigDecimal("100000"))
                        .getResultList();

        accounts.forEach(a ->
                System.out.println(a.getAccountNumber()));
    }

    public static void criteriaQuery(EntityManager em) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> client = cq.from(Client.class);

        cq.select(client)
                .where(cb.like(client.get("lastName"), "J%"));

        List<Client> clients = em.createQuery(cq).getResultList();

        clients.forEach(c ->
                System.out.println(
                        c.getFirstName() + " " + c.getLastName()
                ));
    }

    public static void complexQuery(EntityManager em) {

        Query query = em.createQuery(
                "SELECT c.firstName, a.accountNumber, SUM(t.amount) " +
                        "FROM Client c " +
                        "JOIN c.accounts a " +
                        "JOIN a.transactions t " +
                        "GROUP BY c.firstName, a.accountNumber"
        );

        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {

            String firstName = (String) row[0];
            String accountNumber = (String) row[1];
            Object totalAmount = row[2];

            System.out.println(
                    firstName +
                            " | " +
                            accountNumber +
                            " | Total transactions: " +
                            totalAmount
            );
        }
    }

    public static void loansApprovedByManager(EntityManager em) {

        Query query = em.createQuery(
                "SELECT m.firstName, l.amount, la.approvalStatus " +
                        "FROM LoanApproval la " +
                        "JOIN la.manager m " +
                        "JOIN la.loan l"
        );

        List<Object[]> results = query.getResultList();

        for (Object[] row : results) {

            System.out.println(
                    row[0] + " on loan " +
                            row[1] +
                            " status " +
                            row[2]
            );
        }
    }
}
