package fr.pizzeria.ihm;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {

	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	public boolean execute() {
		
		List<Pizza> pizza = pizzaDao.findAllPizzas();

		return true;
	}

}
