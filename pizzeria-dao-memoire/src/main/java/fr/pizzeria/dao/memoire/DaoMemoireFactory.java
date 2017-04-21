package fr.pizzeria.dao.memoire;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.IPizzaDao;

public class DaoMemoireFactory implements DaoFactory {

	
	private IPizzaDao pizzaDao = null;
	
	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}
	
	

}
