package fr.pizzeria.ihm;

import java.util.Iterator;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class OptionMenu {

	public IPizzaDao pizzaDao;
	public String libelle;

	public OptionMenu(IPizzaDao pizzaDao) {
		super();
		this.pizzaDao = pizzaDao;
	}

	public boolean verifCode(String code) {
		List<Pizza> pizzaTab = pizzaDao.findAllPizzas();

		Iterator<Pizza> iterator = pizzaTab.iterator();
		Pizza pizzaup = iterator.next();
		while (iterator.hasNext() && (!pizzaup.getCode().equals(code))) {
			pizzaup = iterator.next();
		}
		if (pizzaup.getCode().equals(code)) {
			return true;
		}
		return false;
	}

}
