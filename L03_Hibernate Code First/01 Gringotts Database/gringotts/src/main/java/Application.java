import entities.Wizard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Wizard wizard = new Wizard("Pesho", "Peshov", "longNOTES", 33, "Creator", 73, "PGroup", LocalDateTime.now(),
                BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ONE, LocalDateTime.now(), false);

        em.persist(wizard);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
