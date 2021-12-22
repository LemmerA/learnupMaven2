package ru.learnup.javaqa.learnupmvn2.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.Steps;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.StepsLog;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper () {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder().build();
        this.sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public List<Steps> getAllSteps() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Steps> result = session.createQuery("from Steps", Steps.class);
            return result.getResultList();
        }
    }

    public List<StepsLog> getAllStepsLog() {
        try (Session session = sessionFactory.openSession()) {
            final Query<StepsLog> result = session.createQuery("from StepsLog", StepsLog.class);
            return result.getResultList();
        }
    }

    public void addSteps(StepsLog stepsLog) {
        try (Session session = sessionFactory.openSession()) {
            final Transaction logTAction = session.beginTransaction();
            session.save(stepsLog);
            logTAction.commit();

            List<Steps> result = session.createQuery("from Steps where day = :day", Steps.class)
                    .setParameter("day", stepsLog.getDay())
                    .getResultList();

            if (!result.isEmpty()) {
                Steps steps = result.get(0);
                try {
                    steps.setSteps(Math.addExact(steps.getSteps(), stepsLog.getSteps()));
                } catch (ArithmeticException e) {
                    steps.setSteps(Integer.MAX_VALUE);
                }
                steps.setDateUpdate(stepsLog.getDateCreate());

                final Transaction updateTAction = session.beginTransaction();
                session.save(steps);
                updateTAction.commit();
            } else {
                Steps steps = new Steps();
                steps.setDay(stepsLog.getDay());
                steps.setSteps(stepsLog.getSteps());
                steps.setDateCreate(stepsLog.getDateCreate());

                final Transaction createTAction = session.beginTransaction();
                session.save(steps);
                createTAction.commit();
            }

            final Transaction confirmTAction = session.beginTransaction();
            stepsLog.setCommitted(true);
            session.save(stepsLog);
            confirmTAction.commit();
        }
    }
}
