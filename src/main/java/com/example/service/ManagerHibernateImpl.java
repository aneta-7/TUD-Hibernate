package com.example.service;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Bouquet;
import com.example.domain.User;

@Component
@Transactional
public class ManagerHibernateImpl implements Manager{

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);	
	}
	
	@Override
	public List<User> getAllUsers() {
		return sessionFactory.getCurrentSession().getNamedQuery("user.all").list();
	}
	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}
	@Override
	public User findUserById(User user) {
		return (User) sessionFactory.getCurrentSession().get(User.class, user.getId());
	}
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}
	@Override
	public User findUserByNick(String nick) {
		return (User) sessionFactory.getCurrentSession().getNamedQuery("user.byNick").setString("nick",nick).list();
	}
	
	
	
	
	@Override
	public void addNewBouquet(Bouquet bouquet) {
		sessionFactory.getCurrentSession().persist(bouquet);	
	}
	@Override
	public Bouquet findBouquetById(Bouquet bouquet) {
		return (Bouquet) sessionFactory.getCurrentSession().get(Bouquet.class, bouquet.getId());
	}
	@Override
	public List<Bouquet> findBouquetByColor(String color) {
		return sessionFactory.getCurrentSession().getNamedQuery("bouquet.color").setString("color", color).list();
	}
	@Override
	public void updateBouquet(Bouquet bouquet) {
		sessionFactory.getCurrentSession().merge(bouquet);	
		
	}
	//usuawnie kaskadowe
	public void deleteBouquet(User user, Bouquet bouquet) {

		user = (User) sessionFactory.getCurrentSession().get(User.class,user.getId());
		bouquet = (Bouquet) sessionFactory.getCurrentSession().get(Bouquet.class, bouquet.getId());
		
		bouquet.setAvailable(false);
	
		
//		user = (User) sessionFactory.getCurrentSession().get(User.class,user.getId());
//		bouquet = (Bouquet) sessionFactory.getCurrentSession().get(Bouquet.class, bouquet.getId());
//
//		Bouquet toRemove = null;
//		// lazy loading here (person.getCars)
//		for (Bouquet aBouquet : user.getBouquets())
//			if (aBouquet.getId().compareTo(bouquet.getId()) == 0) {
//				toRemove = aBouquet;
//				break;
//			}
//
//		if (toRemove != null)
//			user.getBouquets().remove(toRemove);
//
//		bouquet.setAvailable(false);
	}
	
	@Override
	public List<Bouquet> getAllBouquets() {
		return sessionFactory.getCurrentSession().getNamedQuery("bouquet.all").list();
	}

	@Override
	public boolean getAvailable(Bouquet bouquet) {
		// TODO Auto-generated method stub
		return false;
	}
}
