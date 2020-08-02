package entities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static EntityManager em = null;

    public static  EntityManager getEM()
    {
        if (em == null) {
            Map<String, Object> configOverrides = new HashMap<String, Object>();
                System.out.println("SYSTEM ENV");
            configOverrides.put("javax.persistence.jdbc.password", System.getenv("SPRING_APP_DB_PASSWD"));
            configOverrides.put("javax.persistence.jdbc.user", System.getenv("SPRING_APP_DB_USR"));
            if (System.getenv("SPRING_APP_DB_HOST") != null) {
                String dbUrl = "jdbc:mysql://" + System.getenv("SPRING_APP_DB_HOST") + ":3306/" + System.getenv("SPRING_APP_COMMERCIFY_DB_NAME") + "?serverTimezone=UTC";
                configOverrides.put("javax.persistence.jdbc.url",dbUrl);
                System.out.println("URL CON");
                System.out.println(dbUrl);

            }

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fooddb",configOverrides);
            em = entityManagerFactory.createEntityManager();
        }
        return  em;
    }

    public static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!

        Map<String,String> HerokuSettings = new HashMap<>();
        HerokuSettings.put("hibernate.connection.password",System.getenv("SPRING_APP_DB_PASSWD"));
        HerokuSettings.put("hibernate.connection.user",System.getenv("SPRING_APP_DB_USR"));
        if (System.getenv("SPRING_APP_DB_HOST") != null) {
            String dbUrl = "jdbc:mysql://" + System.getenv("SPRING_APP_DB_HOST") + ":3306/" + System.getenv("SPRING_APP_COMMERCIFY_DB_NAME") + "?serverTimezone=UTC";
            HerokuSettings.put("hibernate.connection.url",dbUrl);
            System.out.println(dbUrl);
        }

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                configure("hibernate.cfg.xml").
                applySettings(HerokuSettings).
                build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            return sessionFactory;
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            System.out.println("Exception at buildSessionFactory ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}
