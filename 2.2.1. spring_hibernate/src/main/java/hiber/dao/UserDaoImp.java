package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "SELECT u FROM User u JOIN FETCH u.car car");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User findUserByCar(Long carSeries, String carModel) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(
              "FROM Car " +
                      "WHERE series = :series AND model = :model");
      query.setParameter("series", carSeries);
      query.setParameter("model", carModel);
      try {
         return query.getSingleResult().getUser();
      } catch (NoResultException nre) {
         return null;
      }
   }

}
