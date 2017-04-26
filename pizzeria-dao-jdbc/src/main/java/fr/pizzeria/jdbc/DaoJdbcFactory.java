package fr.pizzeria.jdbc;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.IPizzaDao;

public class DaoJdbcFactory implements DaoFactory {
	
private IPizzaDao pizzaDao = new PizzaDaoJdbc();

	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}

}
