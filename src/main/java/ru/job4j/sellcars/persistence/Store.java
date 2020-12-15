package ru.job4j.sellcars.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.sellcars.domain.Brand;
import ru.job4j.sellcars.domain.Car;
import ru.job4j.sellcars.domain.Model;
import ru.job4j.sellcars.domain.User;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Store implements SaleDAO, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(Store.class.getName());
    private final SessionFactory sf;

    private Store() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }


    private static final class Lazy {

        private static final Store INSTANCE = new Store();
    }
    public static Store insOf() {
        return Lazy.INSTANCE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
    }

    public <T> void create(final T model) {
        tx(session -> session.save(model));
    }

    public void add(int brandId, int modelId, String town, Calendar calendar, String photo, User user, double parseDouble) {
        tx(session -> {
            Brand brand = session.getReference(Brand.class, brandId);
            Model model = session.getReference(Model.class, modelId);
            Car car = Car.of(brand, model, town, calendar, user, parseDouble);
            if (!"".equals(photo)) {
                car.setPhoto(photo);
            }
            session.persist(car);
            return car;
        });
    }

    public List<Brand> getBrands() {
        return tx(session -> session.createQuery("select b from Brand as b", Brand.class).list());
    }

    public List<Model> getModels(int brand_id) {
        return tx(session -> {
            Brand brand = session.getReference(Brand.class, brand_id);
            return session.createQuery("select m from Model as m where m.brand = :brand", Model.class)
                    .setParameter("brand", brand)
                    .list();
        });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return tx(session -> session.createQuery("select u from User as u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream().findFirst());
    }

    public List<Car> getCars() {
        return tx(session -> session.createQuery("select c from Car as c where c.sold = false", Car.class).list());
    }

    public Optional<Car> getCarById(long id) {
        return tx(session -> session
                .createQuery("select c from Car as c where c.sold = false and c.id = :id", Car.class)
                .setParameter("id", id)
                .getResultStream().findFirst());
    }

    public void soldCar(long id) {
        tx(session -> {
            Car car = session.find(Car.class, id);
            car.setSold(true);
            session.persist(car);
            return car;
        });
    }

    @Override
    public void close() {
        sf.close();
    }
}
