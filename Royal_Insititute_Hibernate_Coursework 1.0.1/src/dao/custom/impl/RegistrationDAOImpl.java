package dao.custom.impl;

import dao.custom.RegistrationDAO;
import entity.Registration;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {

    Session session = FactoryConfiguration.getInstance().getSession();
    @Override
    public boolean add(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Transaction transaction = session.beginTransaction();
        Registration registration = session.load(Registration.class, s);
        session.delete(registration);
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public boolean update(Registration entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Registration find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Registration registration = session.get(Registration.class, s);
        transaction.commit();
        session.close();
        return registration;
    }

    @Override
    public List<Registration> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Registration ");
        List<Registration> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
