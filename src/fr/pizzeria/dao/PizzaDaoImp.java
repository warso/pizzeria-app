package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImp implements IPizzaDao {
	
	
	public List<Pizza> pizzaTab;
	public static int nbrePizza;
	
	public PizzaDaoImp(List<Pizza> pizzaTab) {
		super();
		this.pizzaTab = pizzaTab;
	}
	
	public PizzaDaoImp(){
		
		List tableauPizza = new ArrayList();
		Pizza pizza1 = new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.valueOf("VIANDE"));
		Pizza pizza2 = new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.valueOf("SANS_VIANDE"));
		Pizza pizza3 = new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.valueOf("VIANDE"));
		tableauPizza.add(pizza1);
		tableauPizza.add(pizza2);
		tableauPizza.add(pizza3);
		
		pizzaTab=tableauPizza;
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
	public Pizza findPizzaById(int id) {
		
		return null;
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		
		return null;
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
