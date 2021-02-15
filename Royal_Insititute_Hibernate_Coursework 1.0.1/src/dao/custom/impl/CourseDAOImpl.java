package dao.custom.impl;

import dao.custom.CourseDAO;
import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {


    @Override
    public boolean add(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        Course course = session.load(Course.class, s);
        session.delete(course);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, s);
        transaction.commit();
        session.close();
        return course;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Course ");
        List<Course> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
