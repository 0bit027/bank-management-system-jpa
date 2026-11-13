/*package org.example;

import jakarta.persistence.EntityManager;
import org.example.util.JpaUtil;

public class App {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("JPA successfully initialized!");

        em.close();
        JpaUtil.close();
    }
}*/

package org.example;

import jakarta.persistence.EntityManager;
import org.example.init.DataInitializer;
import org.example.jpql.QueryExamples;
import org.example.util.JpaUtil;

public class App {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        DataInitializer.insertSampleData(em);

        QueryExamples.simpleQuery(em);

        QueryExamples.typedQuery(em);

        QueryExamples.namedQuery(em);

        QueryExamples.multipleNamedQueries(em);

        QueryExamples.criteriaQuery(em);

        QueryExamples.complexQuery(em);

        QueryExamples.loansApprovedByManager(em);

        em.close();
        JpaUtil.close();

        System.out.println("Sample data inserted.");
    }
}