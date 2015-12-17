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
	
	@SuppressWarnings("unchecked")
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
		user.setId(null);
		sessionFactory.getCurrentSession().persist(user);
	}
	@Override
	public User findUserByNick(String nick) {
		return (User) sessionFactory.getCurrentSession().getNamedQuery("user.byNick").setString("nick",nick).list();
	}
	
	
	@Override
	public void addNewBouquet(Bouquet bouquet) {
		bouquet.setId(null);
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
	@Override
	public void deleteBouquet(Bouquet bouquet) {

	}
	
	@Override
	public List<Bouquet> getAllBouquets() {
		return sessionFactory.getCurrentSession().getNamedQuery("bouquet.all").list();
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Bouquet> getAvailableBouquets(User user) {
		return sessionFactory.getCurrentSession().getNamedQuery("bouquet.unsold").list();
	}
	@Override
	public void disposeBouquet(User user, Bouquet bouquet) {

	}

	public void sellBouqet(Long userId, Long bouquetId) {
		
	}


	@Override
	public List<Bouquet> getOwnedBouquets(User user) {
	
		return null;
	}


	@Override
	public List<Bouquet> getAvailableBouquets() {
		
		return null;
	}



}
