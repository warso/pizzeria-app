package fr.pizzeria.ihm;

import java.util.Iterator;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {

	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	public List<Pizza> execute() {
		
		List<Pizza> pizza = pizzaDao.findAllPizzas();
		Iterator<Pizza> iterator = pizza.iterator();
		while (iterator.hasNext()) {
			Pizza pizza1 = iterator.next();
			System.out.println(pizza1.getCode() + "->" + " " + pizza1.getNom() + " " + "(" + pizza1.getPrix() + ")" + " " + pizza1.getCat());
		}
			return pizza;
	
	}

}
