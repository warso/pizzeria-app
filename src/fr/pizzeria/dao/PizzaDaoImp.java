package fr.pizzeria.dao;

import java.util.Iterator;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImp implements IPizzaDao {
	
	
	public List<Pizza> pizzaTab;
	public static int nbrePizza;
	
	public PizzaDaoImp(List<Pizza> pizzaTab) {
		super();
		this.pizzaTab = pizzaTab;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		Iterator<Pizza> iterator = pizzaTab.iterator();
		while (iterator.hasNext()) {
			Pizza pizza = iterator.next();
			System.out.println(pizza.getCode() + "->" + " " + pizza.getNom() + " " + "(" + pizza.getPrix() + ")" + " " + pizza.cat);
		}
		
		return pizzaTab;
	}

	@Override
	public boolean saveNewPizza(Pizza pizzas)  {
		
		pizzaTab.add(pizzas);
		nbrePizza += 1;
	
		return false;
	}

	@Override
	public boolean updatePizza(String code, Pizza pizza) {
		
		
		Iterator<Pizza> iterator = pizzaTab.iterator();
		Pizza pizzaup = iterator.next();
		while (iterator.hasNext() && (!pizzaup.getCode().equals(code))) {
			pizzaup = iterator.next();
		}
		
		pizzaup.setCode(pizza.getCode());
		pizzaup.setNom(pizza.getNom());
		pizzaup.setPrix(pizza.getPrix());
		return false;
	}

	@Override
	public boolean deletePizza(String code) {
		
		Iterator<Pizza> iterator = pizzaTab.iterator();
		Pizza pizza = iterator.next();
		while (iterator.hasNext() && (!pizza.getCode().equals(code))) {
			pizza = iterator.next();
			nbrePizza-=1;
		}
		
		pizzaTab.remove(pizza);
		return false;
	}
	
	
	
	

}
