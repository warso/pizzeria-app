package fr.pizzeria.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.pizzeria.dao.DeletePizzaException;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.SavePizzaException;
import fr.pizzeria.dao.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	EntityManagerFactory emfact;

	public PizzaDaoJpa(EntityManagerFactory emfact) {
		super();
		this.emfact = emfact;
	}

	@Override
	public List<Pizza> findAllPizzas() {

		TypedQuery<Pizza> query = emfact.createEntityManager().createQuery("select p from Pizza p", Pizza.class);
		return query.getResultList();
	}

	@Override
	public Pizza findPizzaById(int id) {

		return null;
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		EntityManager em = emfact.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Pizza p1 = new Pizza();
		p1.setId(pizza.getId());
		p1.setNom(pizza.getNom());
		p1.setCode(pizza.getCode());
		p1.setPrix(pizza.getPrix());
		p1.setCat(pizza.getCat());
		em.persist(p1);
		et.commit();
		em.close();
		return false;
	}

	@Override
	public boolean updatePizza(String code, Pizza pizza) throws UpdatePizzaException {
		EntityManager em = emfact.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", code);
		Pizza p2 = query.getResultList().get(0);

		EntityTransaction et = em.getTransaction();
		et.begin();
		if (p2 != null) {
			p2.setCode(pizza.getCode());
			p2.setNom(pizza.getNom());
			p2.setPrix(pizza.getPrix());
			p2.setCat(pizza.getCat());
		}
		et.commit();
		em.close();
		return false;
	}

	@Override
	public boolean deletePizza(String code) throws DeletePizzaException {

		EntityManager em = emfact.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", code);
		Pizza p3 = query.getResultList().get(0);

		EntityTransaction et = em.getTransaction();
		et.begin();
		if (p3 != null) {
			em.remove(p3);
		}
		et.commit();
		em.close();
		return false;
	}

}
